package uz.sardorbroo.otp.storage.impl;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.otp.exception.AlreadyExistException;
import uz.sardorbroo.otp.exception.InvalidArgumentException;
import uz.sardorbroo.otp.exception.NotFoundException;
import uz.sardorbroo.otp.storage.Storage;
import uz.sardorbroo.otp.storage.dto.OtpDto;

import java.util.*;

@Slf4j
public class DefaultMemoryMapStorage implements Storage {

    private static final Map<UUID, OtpDto> STORAGE = new HashMap<>();

    @Override
    public Optional<OtpDto> save(OtpDto dto, int expirationTime) {
        log.debug("Store OtpDto to HashMap with expiration. OtpDto: {} | Expiration: {}", dto, expirationTime);

        if (getById(dto.getId()).isPresent()) {
            log.warn("Data is already exist in storage! OtpDto: {}", dto);
            throw new AlreadyExistException("Data is already exist in storage! OtpDto: " + dto);
        }

        STORAGE.put(dto.getId(), dto);
        log.debug("Data has stored successfully! OtpDto: {}", dto);
        return Optional.of(dto);
    }

    @Override
    public Optional<OtpDto> save(OtpDto dto) {
        log.debug("Store data to HashMap without expiration. OtpDto: {}", dto);
        return save(dto, 0);
    }

    @Override
    public Optional<OtpDto> update(OtpDto dto) {
        log.debug("Update stored data in HashMap. OtpDto: {}", dto);

        if (getById(dto.getId()).isEmpty()) {
            log.warn("Stored data is not found in storage! OtpDto: {}", dto);
            throw new NotFoundException("Stored data is not found in storage! OtpDto: " + dto);
        }

        STORAGE.put(dto.getId(), dto);
        log.debug("Stored data has updated successfully. OtpDto: {}", dto);
        return Optional.of(dto);
    }

    @Override
    public Optional<OtpDto> getById(UUID id) {
        log.debug("Get stored data from storage by ID. ID: {}", id);

        if (Objects.isNull(id)) {
            log.warn("Invalid argument is passed! ID must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! ID must not be null!");
        }

        Optional<OtpDto> storedDataOptional = Optional.ofNullable(STORAGE.get(id));
        log.info("Is stored data found? Result: {}", storedDataOptional.isPresent());
        return storedDataOptional;
    }

    @Override
    public Optional<OtpDto> delete(UUID id) {
        log.debug("Remove stored data from storage by ID. ID: {}", id);

        if (getById(id).isEmpty()) {
            log.warn("Stored data not found in storage by given ID! ID: {}", id);
            throw new NotFoundException("Stored data not found in storage by given ID! ID: {}" + id);
        }

        OtpDto storedData = STORAGE.remove(id);
        log.debug("Stored data is removed successfully. OtpDto: {}", storedData);
        return Optional.of(storedData);
    }
}
