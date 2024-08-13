package uz.sardorbroo.otp.messages;

import uz.sardorbroo.otp.generator.dto.GeneratedOtpDTO;
import uz.sardorbroo.otp.enumeration.Language;

import java.util.Optional;

public interface MessageSource {

    Optional<String> getMessage(Language language, GeneratedOtpDTO otp);

}
