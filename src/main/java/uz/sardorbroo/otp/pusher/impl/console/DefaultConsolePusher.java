package uz.sardorbroo.otp.pusher.impl.console;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.otp.pusher.Pusher;
import uz.sardorbroo.otp.pusher.dto.DestinationDto;
import uz.sardorbroo.otp.pusher.dto.PushResultDto;
import uz.sardorbroo.otp.pusher.dto.console.ConsoleResultDto;

import java.util.Optional;

@Slf4j
public class DefaultConsolePusher implements Pusher {

    @Override
    public Optional<? extends PushResultDto> push(DestinationDto destination, String message) {
        log.debug("Console pusher pushes message. DestinationDto: {} | Message: {}", destination, message);

        System.out.println("Message has pushed to destination. \nMessage: " + message + "\n" + "Destination: " + destination);
        PushResultDto result = new ConsoleResultDto(message);
        return Optional.of(result);
    }
}
