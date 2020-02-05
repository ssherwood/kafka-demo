package com.example.kafkademo.streams;

import io.confluent.developer.User;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.example.kafkademo.streams.Bindings.USERS;
import static com.example.kafkademo.streams.Bindings.USERS_FILTERED;

@Component
@CommonsLog(topic = "Stream Logger")
class UserProcessor {

    @StreamListener
    @SendTo(USERS_FILTERED)
    KStream<String, User> processUsers(@Input(USERS) KStream<String, User> inputStream) {
        return inputStream
                .filter((key, user) -> user.getAge() < 40)
                .mapValues(user -> new User(user.getName().toUpperCase(), user.getAge()))
                .peek((key, user) -> log.info("New entry in filtered stream => Key = " + key + " Value = " + user));
    }
}
