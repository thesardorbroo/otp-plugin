package uz.sardorbroo.otp.exception;

public class OtpException extends RuntimeException {

    private final String message;

    public OtpException(String message) {
        super(message);
        this.message = message;
    }
}
