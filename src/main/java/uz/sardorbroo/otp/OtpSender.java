package uz.sardorbroo.otp;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.otp.dto.OtpRequestDto;
import uz.sardorbroo.otp.exception.OtpException;
import uz.sardorbroo.otp.exception.TooManyRequestToOtp;
import uz.sardorbroo.otp.generator.Generator;
import uz.sardorbroo.otp.generator.dto.GeneratedOtpDTO;
import uz.sardorbroo.otp.messages.MessageSource;
import uz.sardorbroo.otp.properties.OtpProperties;
import uz.sardorbroo.otp.pusher.Pusher;
import uz.sardorbroo.otp.storage.Storage;
import uz.sardorbroo.otp.storage.dto.OtpDto;
import uz.sardorbroo.otp.utils.StringUtils;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OtpSender {

    private OtpProperties properties;
    private Generator generator;
    private Storage storage;
    private Collection<Pusher> pushers;
    private MessageSource messageSource;

    protected OtpSender(OtpProperties properties, Generator generator, Storage storage, Collection<Pusher> pushers, MessageSource messageSource) {
        this.properties = properties;
        this.generator = generator;
        this.storage = storage;
        this.pushers = pushers;
        this.messageSource = messageSource;
    }

    public Optional<Boolean> send(OtpRequestDto dto) {
        log.info("Send OTP to user. OtpRequestDTO: {}", dto);

        if (!hasOtpIntervalTimePass(dto.getId())) {
            log.warn("Too many request to send OTP! OTP interval has not passed yet!");
            throw new TooManyRequestToOtp();
        }

        GeneratedOtpDTO generatedOtp = generator.generate();

        Optional<OtpDto> savedOtpOptional = saveOtpToStorage(dto.getId(), generatedOtp);
        if (savedOtpOptional.isEmpty()) {
            log.warn("Something went wrong! Generated OTP hasn't saved successfully!");
            throw new OtpException("Something went wrong! Generated OTP hasn't saved successfully!");
        }

        Optional<String> messageOptional = messageSource.getMessage(dto.getLanguage(), generatedOtp);
        if (messageOptional.isEmpty()) {
            log.warn("OTP message is not found!");
            throw new OtpException("OTP message is not found!");
        }

        pushers.forEach(pusher -> pusher.push(dto.getDestination(), messageOptional.orElseThrow()));

        log.debug("OTP has send! OtpRequestDTO: {} | Code: {}", dto, StringUtils.mask(generatedOtp.getCode()));
        return Optional.of(Boolean.TRUE);
    }

    private Optional<OtpDto> saveOtpToStorage(UUID id, GeneratedOtpDTO generatedOtp) {

        OtpDto dto = new OtpDto().setId(id).setCode(generatedOtp.getCode()).setCreatedAt(Instant.now());

        return storage.save(dto, properties.getExpiration());
    }

    private Boolean hasOtpIntervalTimePass(UUID id) {
        log.info("Checking OTP interval passed or not. ID: {}", id);

        Optional<OtpDto> otpOptional = storage.getById(id);
        if (otpOptional.isEmpty()) {
            log.info("OTP is not found by given ID! ID: {}", id);
            return true;
        }

        Instant time = Instant.now().minus(properties.getInterval(), TimeUnit.SECONDS.toChronoUnit());
        return otpOptional.orElseThrow().getCreatedAt().compareTo(time) <= 0;
    }
}
