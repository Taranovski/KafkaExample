package com.dark.future.kafka_simple.stream.serde;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import com.dark.future.kafka_simple.stream.serde.deserializer.ReadFromInitialMessageCustomCoreConceptKeyDeserializer;
import com.dark.future.kafka_simple.stream.serde.serializer.CustomCoreConceptKeySerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
@Component
public class ReadFromInitialMessageCustomCoreConceptKeySerde implements Serde<CustomCoreConceptKey> {

    public static final Serializer<CustomCoreConceptKey> SERIALIZER = new CustomCoreConceptKeySerializer();
    public static final Deserializer<CustomCoreConceptKey> DESERIALIZER = new ReadFromInitialMessageCustomCoreConceptKeyDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<CustomCoreConceptKey> serializer() {
        return SERIALIZER;
    }

    @Override
    public Deserializer<CustomCoreConceptKey> deserializer() {
        return DESERIALIZER;
    }

}
