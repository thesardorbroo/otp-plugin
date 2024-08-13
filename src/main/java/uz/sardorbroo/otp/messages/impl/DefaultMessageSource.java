package uz.sardorbroo.otp.messages.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import uz.sardorbroo.otp.generator.dto.GeneratedOtpDTO;
import uz.sardorbroo.otp.enumeration.Language;
import uz.sardorbroo.otp.exception.InvalidArgumentException;
import uz.sardorbroo.otp.messages.MessageSource;
import uz.sardorbroo.otp.utils.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class DefaultMessageSource implements MessageSource {

    private static final String OTP_CODE_KEY_IN_MESSAGE = "OTP_CODE";
    private static final String OTP_CODE_KEY_FOR_MESSAGE = "${" + OTP_CODE_KEY_IN_MESSAGE + "}";

    private static final Map<Language, String> MESSAGES_BY_LANGUAGE = Map.of(
            Language.EN, "Don't share one time code with another people! Code: " + OTP_CODE_KEY_FOR_MESSAGE,
            Language.RU, "Не сообщайте одноразовый временной код другим людям! Код: " + OTP_CODE_KEY_FOR_MESSAGE,
            Language.UZ, "Bir martalik kodni hech kimga bermang! Kod: " + OTP_CODE_KEY_FOR_MESSAGE
    );

    @Override
    public Optional<String> getMessage(Language language, GeneratedOtpDTO otp) {
        log.debug("Get message by language for OTP. Language: {} | GeneratedOtpDto: {}", language, otp);

        if (Objects.isNull(language)) {
            log.warn("Invalid argument is passed! Language must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! Language must not be null!");
        }
        if (Objects.isNull(otp)) {
            log.warn("Invalid argument is passed! GeneratedOtpDTO must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! GeneratedOtpDTO must not be null!");
        }

        if (!MESSAGES_BY_LANGUAGE.containsKey(language)) {
            log.warn("OTP template message is not found in map by given Language! Language: {}", language);
            return Optional.empty();
        }

        String template = MESSAGES_BY_LANGUAGE.get(language);
        String message = buildMessage(template, otp.getCode());

        log.debug("OTP message is found.");
        return Optional.of(message);
    }

    private String buildMessage(String template, String otp) {
        log.debug("Build OTP message by template and OTP code. OTP code: {}", StringUtils.mask(otp));

        StringSubstitutor sub = new StringSubstitutor(Map.of(OTP_CODE_KEY_IN_MESSAGE, otp));
        String message = sub.replace(template);

        log.info("OTP message is found");
        log.debug("OTP message: {}", message);
        return message;
    }
}
