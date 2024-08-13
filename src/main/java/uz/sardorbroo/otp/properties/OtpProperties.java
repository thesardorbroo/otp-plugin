package uz.sardorbroo.otp.properties;

import lombok.Data;
import uz.sardorbroo.otp.exception.InvalidArgumentException;
import uz.sardorbroo.otp.utils.NumberUtils;

@Data
public class OtpProperties {

    private int expiration = 300; // in seconds

    private int interval = 60; // in seconds

    public void setExpiration(int expiration) {
        if (!NumberUtils.isIntPositive(expiration, false)) {
            throw new InvalidArgumentException("Invalid argument is passed! Expiration must not be negative! Zero is negative");
        }
        this.expiration = expiration;
    }

    public OtpProperties expiration(int expiration) {
        this.setExpiration(expiration);
        return this;
    }

    public void setInterval(int interval) {
        if (!NumberUtils.isIntPositive(interval, false)) {
            throw new InvalidArgumentException("Invalid argument is passed! Interval must not be negative! Zero is negative");
        }
        this.interval = interval;
    }

    public OtpProperties interval(int interval) {
        this.setInterval(interval);
        return this;
    }
}
