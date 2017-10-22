package com.dark.future.kafka_simple.stream.serde.deserializer;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class ReadFromInitialMessageCustomCoreConceptKeyDeserializer implements Deserializer<CustomCoreConceptKey> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public CustomCoreConceptKey deserialize(String topic, byte[] data) {
        String rawMessage = new String(data);

        String[] split = rawMessage.split("\\w+");

        return new CustomCoreConceptKey(split[0], split[1]);
    }

    @Override
    public void close() {

    }
}
