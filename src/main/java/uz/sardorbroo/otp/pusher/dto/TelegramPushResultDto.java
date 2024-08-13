package uz.sardorbroo.otp.pusher.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TelegramPushResultDto extends PushResultDto {

    private final String response;

}
