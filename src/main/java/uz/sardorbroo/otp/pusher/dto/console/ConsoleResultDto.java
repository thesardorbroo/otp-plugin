package uz.sardorbroo.otp.pusher.dto.console;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sardorbroo.otp.pusher.dto.PushResultDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsoleResultDto extends PushResultDto {

    private String message;

}
