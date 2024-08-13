package uz.sardorbroo.otp.storage;

import uz.sardorbroo.otp.storage.dto.OtpDto;

import java.util.Optional;
import java.util.UUID;

public interface Storage {

    Optional<OtpDto> save(OtpDto value, int expirationTime);

    Optional<OtpDto> save(OtpDto value);

    Optional<OtpDto> update(OtpDto value);

    Optional<OtpDto> getById(UUID id);

    Optional<OtpDto> delete(UUID id);

}
