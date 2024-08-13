package uz.sardorbroo.otp.utils;

public class NumberUtils {

    public static boolean isIntPositive(int number) {
        return isIntPositive(number, true);
    }

    public static boolean isIntPositive(int number, boolean isZeroPositive) {
        return isZeroPositive ? number >= 0 : number > 0;
    }

    public static boolean isLongPositive(long number) {
        return isLongPositive(number, true);
    }

    public static boolean isLongPositive(long number, boolean isZeroPositive) {
        return isZeroPositive ? number >= 0 : number > 0;
    }

    public static boolean isShortPositive(short number) {
        return isShortPositive(number, true);
    }

    public static boolean isShortPositive(short number, boolean isZeroPositive) {
        return isZeroPositive ? number >= 0 : number > 0;
    }
}
