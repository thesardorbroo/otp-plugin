package uz.sardorbroo.otp.pusher.impl.telegram.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.otp.exception.InvalidArgumentException;
import uz.sardorbroo.otp.pusher.Pusher;
import uz.sardorbroo.otp.pusher.dto.DestinationDto;
import uz.sardorbroo.otp.pusher.dto.PushResultDto;
import uz.sardorbroo.otp.pusher.dto.TelegramPushResultDto;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.SendMessage;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.client.TelegramBotClient;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class TelegramBotPusher implements Pusher {

    private final TelegramBotClient client;

    @Override
    public Optional<? extends PushResultDto> push(DestinationDto destination, String message) {
        log.debug("Push message to destination via Telegram. Destination: {} | Message: {}", destination, message);

        if (Objects.isNull(destination)) {
            log.warn("Invalid argument is passed! Destination must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! Destination must not be null!");
        }
        if (StringUtils.isBlank(message)) {
            log.warn("Invalid argument is passed! Message must not be blank!");
            throw new InvalidArgumentException("Invalid argument is passed! Message must not be blank!");
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(destination.getContact());
        sendMessage.setText(message);

        Optional<String> telegramResponseOptional = client.sendMessage(sendMessage);
        log.debug("Message has pushed via telegram. Result: {}", telegramResponseOptional.isPresent());
        return telegramResponseOptional.map(response -> new TelegramPushResultDto(response));
    }
}
