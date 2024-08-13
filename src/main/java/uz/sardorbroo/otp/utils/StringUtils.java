package uz.sardorbroo.otp.utils;

import uz.sardorbroo.otp.helper.OtpMaskHelper;
import uz.sardorbroo.otp.helper.impl.DefaultOtpMaskHelper;

public class StringUtils {

    private static final OtpMaskHelper MASKER = new DefaultOtpMaskHelper();

    public static String mask(String source) {
        return MASKER.mask(source);
    }

    public static String mask(OtpMaskHelper masker, String code) {
        return masker.mask(code);
    }

    /**
     * Checks source and throws exception if source is blank!
     *
     * @param source
     * @param message
     * @throws IllegalArgumentException
     */
    public static void requiresNotBlank(String source, String message) {
        if (org.apache.commons.lang3.StringUtils.isBlank(source)) {
            throw new IllegalArgumentException(message);
        }
    }
}
