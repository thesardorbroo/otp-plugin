package uz.sardorbroo.otp.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.sardorbroo.otp.utils.StringUtils;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OtpDto {

    private UUID id;

    private String code;

    private Instant createdAt;

    @Override
    public String toString() {
        return "OtpDTO{" +
                "id=" + id +
                ", code='" + StringUtils.mask(code) + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
