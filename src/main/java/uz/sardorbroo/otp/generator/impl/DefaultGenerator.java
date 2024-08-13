package uz.sardorbroo.otp.generator.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import uz.sardorbroo.otp.generator.dto.GeneratedOtpDTO;
import uz.sardorbroo.otp.exception.InvalidOtpGeneratorRangeException;
import uz.sardorbroo.otp.generator.Generator;
import uz.sardorbroo.otp.utils.NumberUtils;

@Slf4j
public class DefaultGenerator implements Generator {

    private int start = 0;
    private int end = 9999;

    private DefaultGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public GeneratedOtpDTO generate() {
        log.debug("Generate OTP code");

        int code = RandomUtils.nextInt(this.start, this.end);

        GeneratedOtpDTO result = new GeneratedOtpDTO();
        result.setCode(String.valueOf(code));

        log.debug("OTP code has generated successfully. GeneratedOtpDTO: {}", result);
        return result;
    }

    public static class Builder {

        private int start = 0;
        private int end = 9999;

        public void start(int start) {
            throwIfNumberIsNegative(start);
            this.start = start;
        }

        public void end(int end) {
            throwIfNumberIsNegative(end);
            this.end = end;
        }

        public DefaultGenerator build() {
            return new DefaultGenerator(this.start, this.end);
        }

        private void throwIfNumberIsNegative(int number) {
            if (!NumberUtils.isIntPositive(number, true)) {
                throw new InvalidOtpGeneratorRangeException("Invalid argument is passed! Number must not be negative! Number: " + number);
            }
        }
    }
}
