package uz.sardorbroo.otp;

import org.junit.jupiter.api.Test;
import uz.sardorbroo.otp.dto.OtpRequestDto;
import uz.sardorbroo.otp.enumeration.Language;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.client.impl.DefaultRestTelegramBotClient;
import uz.sardorbroo.otp.pusher.dto.telegram.bot.config.TelegramBotProperties;
import uz.sardorbroo.otp.pusher.impl.telegram.bot.TelegramBotStaticGroupsPusher;

import java.util.List;
import java.util.UUID;

public class OtpSenderTest {

    @Test
    public void test() {
        var telegramBotProperties = new TelegramBotProperties();
        telegramBotProperties.setUsername("avtomatizatsiya_bot");
        telegramBotProperties.setToken("5142075282:AAHLMwMhLZBGGxgoAfZWOC2yC94gBs6SPSI");
        telegramBotProperties.setActive(true);

        var client = new DefaultRestTelegramBotClient(telegramBotProperties);

        var pusher = new TelegramBotStaticGroupsPusher(client, List.of("-4270517138"));

        var otpSender = new OtpSenderBuilder()
                .pushers(List.of(pusher))
                .build();

        var dto = new OtpRequestDto();
        dto.setLanguage(Language.EN);
        dto.setId(UUID.randomUUID());

        otpSender.send(dto);

    }
}
