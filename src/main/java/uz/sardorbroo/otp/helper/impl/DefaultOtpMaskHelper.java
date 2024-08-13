package uz.sardorbroo.otp.helper.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.otp.helper.OtpMaskHelper;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DefaultOtpMaskHelper implements OtpMaskHelper {

    private String mask = "****";
    private int length = 2;

    @Override
    public String mask(String code) {
        log.debug("Masking OTP code");

        String maskedCode = StringUtils.abbreviateMiddle(code, this.mask, length);
        log.debug("OTP code has masked. Masked code: {}", maskedCode);
        return maskedCode;
    }
}
