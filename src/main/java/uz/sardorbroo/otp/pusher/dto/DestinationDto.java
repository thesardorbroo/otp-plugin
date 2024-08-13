package uz.sardorbroo.otp.pusher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.sardorbroo.otp.utils.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DestinationDto {

    private String contact;

    public void setContact(String contact) {
        if (org.apache.commons.lang3.StringUtils.isBlank(contact)) {
            throw new IllegalArgumentException("Invalid argument is passed! Contact must not be blank!");
        }
        this.contact = contact;
    }

    public DestinationDto contact(String contact) {
        this.setContact(contact);
        return this;
    }

    @Override
    public String toString() {
        return "DestinationDto{" +
                "contact='" + StringUtils.mask(contact) + '\'' +
                '}';
    }
}
