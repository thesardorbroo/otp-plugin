package uz.sardorbroo.otp.pusher.dto.telegram.bot.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.sardorbroo.otp.utils.StringUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TelegramBotProperties {

    private String token;

    private String username;

    private boolean active = true;

    public void setToken(String token) {
        StringUtils.requiresNotBlank(token, "Invalid argument is passed! Token must not be blank!");
        this.token = token;
    }

    public TelegramBotProperties token(String token) {
        this.setToken(token);
        return this;
    }

    public void setUsername(String username) {
        StringUtils.requiresNotBlank(username, "Invalid argument is passed! Username must not be blank!");
        this.username = username;
    }

    public TelegramBotProperties username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TelegramBotProperties active(boolean active) {
        this.setActive(active);
        return this;
    }
}
