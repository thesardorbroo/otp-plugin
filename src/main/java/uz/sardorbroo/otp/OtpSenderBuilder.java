package uz.sardorbroo.otp;

import uz.sardorbroo.otp.generator.Generator;
import uz.sardorbroo.otp.generator.impl.DefaultGenerator;
import uz.sardorbroo.otp.messages.MessageSource;
import uz.sardorbroo.otp.messages.impl.DefaultMessageSource;
import uz.sardorbroo.otp.properties.OtpProperties;
import uz.sardorbroo.otp.pusher.Pusher;
import uz.sardorbroo.otp.pusher.impl.console.DefaultConsolePusher;
import uz.sardorbroo.otp.storage.Storage;
import uz.sardorbroo.otp.storage.impl.DefaultMemoryMapStorage;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class OtpSenderBuilder implements Builder<OtpSender> {

    private OtpProperties properties = new OtpProperties();
    private Generator generator = new DefaultGenerator.Builder().build();
    private Storage storage = new DefaultMemoryMapStorage();
    private Collection<Pusher> pushers = List.of(new DefaultConsolePusher());
    private MessageSource messageSource = new DefaultMessageSource();

    public OtpSenderBuilder properties(OtpProperties properties) {
        Objects.requireNonNull(properties, "Invalid argument is passed! OtpProperties must not be null!");
        this.properties = properties;
        return this;
    }

    public OtpSenderBuilder generator(Generator generator) {
        Objects.requireNonNull(generator, "Invalid argument is passed! Generator must not be null!");
        this.generator = generator;
        return this;
    }

    public OtpSenderBuilder storage(Storage storage) {
        Objects.requireNonNull(storage, "Invalid argument is passed! Storage must not be null!");
        this.storage = storage;
        return this;
    }

    public OtpSenderBuilder pushers(Collection<Pusher> pushers) {
        Objects.requireNonNull(pushers, "Invalid argument is passed! Pushers must not be null!");
        this.pushers = pushers;
        return this;
    }

    public OtpSenderBuilder messageSource(MessageSource messageSource) {
        Objects.requireNonNull(messageSource, "Invalid argument is passed! MessageSource must not be null!");
        this.messageSource = messageSource;
        return this;
    }

    @Override
    public OtpSender build() {
        return new OtpSender(properties, generator, storage, pushers, messageSource);
    }
}
