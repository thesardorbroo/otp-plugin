package uz.sardorbroo.otp.exception;

public class TooManyRequestToOtp extends OtpException {

    private static final String DEFAULT_MESSAGE = "Too many request to send OTP! OTP interval has not passed yet!";

    public TooManyRequestToOtp(String message) {
        super(message);
    }

    public TooManyRequestToOtp() {
        super(DEFAULT_MESSAGE);
    }
}
