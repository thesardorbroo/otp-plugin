package uz.sardorbroo.otp.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.sardorbroo.otp.utils.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeneratedOtpDTO {

    private String code;

    @Override
    public String toString() {
        return "GeneratedOtpDTO{" +
                "code='" + StringUtils.mask(code) + '\'' +
                '}';
    }
}
