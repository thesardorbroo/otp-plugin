package uz.sardorbroo.otp.pusher;

import uz.sardorbroo.otp.pusher.dto.DestinationDto;
import uz.sardorbroo.otp.pusher.dto.PushResultDto;

import java.util.Optional;

public interface Pusher {

    Optional<? extends PushResultDto> push(DestinationDto destination, String message);

}
