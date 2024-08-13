package uz.sardorbroo.otp.pusher.dto.telegram.bot.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.sardorbroo.otp.exception.InvalidArgumentException;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.SendMessage;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.client.TelegramBotClient;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.config.TelegramBotProperties;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class DefaultRestTelegramBotClient implements TelegramBotClient {
    private static final String TELEGRAM_BASE_URI = "https://api.telegram.org/bot";

    private final TelegramBotProperties properties;
    private final URI root;
    private final RestTemplate client;

    public DefaultRestTelegramBotClient(TelegramBotProperties properties) {
        this.properties = properties;
        this.root = buildRootUri(properties);
        this.client = new RestTemplate();
    }

    private URI buildRootUri(TelegramBotProperties properties) {
        return URI.create(TELEGRAM_BASE_URI + properties.getToken() + "/");
    }

    private URI buildSpecificUri(String path) {
        return root.resolve(path);
    }

    @Override
    public Optional<String> sendMessage(SendMessage sendMessage) {
        log.debug("Send message to Telegram with REST client. SendMessage: {}", sendMessage);

        if (Objects.isNull(sendMessage)) {
            log.warn("Invalid argument is passed! SendMessage must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! SendMessage must not be null!");
        }

        URI uri = buildSpecificUri("./sendMessage");

        System.out.println(uri);
        ResponseEntity<String> response = client.exchange(uri, HttpMethod.POST, new HttpEntity<>(sendMessage), String.class);

        log.debug("Message has send to Telegram. Response: {}", response);
        return Optional.ofNullable(response.getBody());
    }
}
