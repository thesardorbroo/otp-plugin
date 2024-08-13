package uz.sardorbroo.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sardorbroo.otp.enumeration.Language;
import uz.sardorbroo.otp.pusher.dto.DestinationDto;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequestDto {

    private UUID id;

    private DestinationDto destination;

    private Language language;

    public void setId(UUID id) {
        Objects.requireNonNull(id, "Invalid argument is passed! ID must not be null!");
        this.id = id;
    }

    public OtpRequestDto id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setDestination(DestinationDto destination) {
        Objects.requireNonNull(destination, "Invalid argument is passed! DestinationDto must not be null!");
        this.destination = destination;
    }

    public OtpRequestDto destination(DestinationDto destination) {
        this.setDestination(destination);
        return this;
    }

    public void setLanguage(Language language) {
        Objects.requireNonNull(language, "Invalid argument is passed! Locale must not be null!");
        this.language = language;
    }

    public OtpRequestDto language(Language language) {
        this.setLanguage(language);
        return this;
    }
}
