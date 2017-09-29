package com.dark.future.kafka_simple.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Titan on 26.09.2017.
 */
@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payload) {
        LOGGER.info("received payload='{}'", payload);
    }
}
