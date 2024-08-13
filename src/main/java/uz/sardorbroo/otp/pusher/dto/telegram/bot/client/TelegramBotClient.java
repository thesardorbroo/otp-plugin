package uz.sardorbroo.otp.pusher.dto.telegram.bot.client;

import uz.sardorbroo.otp.pusher.dto.telegram.bot.SendMessage;

import java.util.Optional;

public interface TelegramBotClient {

    Optional<String> sendMessage(SendMessage sendMessage);

}
