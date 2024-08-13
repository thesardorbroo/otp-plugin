package uz.sardorbroo.otp.pusher.dto.telegram.bot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.sardorbroo.otp.utils.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessage {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("parse_mode")
    private String parseMode = "HTML";

    public void setChatId(String chatId) {
        StringUtils.requiresNotBlank(chatId, "Invalid argument is passed! ChatID must not be blank!");
        this.chatId = chatId;
    }

    public SendMessage chatId(String chatId) {
        this.setChatId(chatId);
        return this;
    }

    public void setText(String text) {
        StringUtils.requiresNotBlank(text, "Invalid argument is passed! Text must not be blank!");
        this.text = text;
    }

    public SendMessage text(String text) {
        this.setText(text);
        return this;
    }
}
