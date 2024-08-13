package uz.sardorbroo.otp.pusher.impl.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.otp.pusher.Pusher;
import uz.sardorbroo.otp.pusher.dto.DestinationDto;
import uz.sardorbroo.otp.pusher.dto.PushResultDto;
import uz.sardorbroo.otp.pusher.dto.TelegramPushResultDto;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.client.TelegramBotClient;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class TelegramBotStaticGroupsPusher implements Pusher {

    private final Pusher pusher;
    private final Set<DestinationDto> destinations;

    public TelegramBotStaticGroupsPusher(TelegramBotClient client, Collection<String> groups) {
        this.pusher = new TelegramBotPusher(client);
        this.destinations = initializeStaticDestinations(groups);
    }

    private Set<DestinationDto> initializeStaticDestinations(Collection<String> groups) {
        return groups.stream()
                .map(group -> new DestinationDto().contact(group))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<? extends PushResultDto> push(DestinationDto unused, String message) {
        log.debug("Push message to Telegram static groups. Message: {}", message);

        destinations.forEach(destination -> pusher.push(destination, message));

        PushResultDto result = new TelegramPushResultDto("OK");

        log.debug("Message has pushed to Telegram static groups successfully");
        return Optional.of(result);
    }
}
