package com.dark.future.kafka_simple.stream.serde;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import com.dark.future.kafka_simple.stream.serde.deserializer.CustomCoreConceptKeyDeserializer;
import com.dark.future.kafka_simple.stream.serde.serializer.CustomCoreConceptKeySerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class CustomCoreConceptKeySerde implements Serde<CustomCoreConceptKey>{

    public static final CustomCoreConceptKeySerializer CUSTOM_CORE_CONCEPT_KEY_SERIALIZER = new CustomCoreConceptKeySerializer();
    public static final Deserializer<CustomCoreConceptKey> DESERIALIZER = new CustomCoreConceptKeyDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<CustomCoreConceptKey> serializer() {
        return CUSTOM_CORE_CONCEPT_KEY_SERIALIZER;
    }

    @Override
    public Deserializer<CustomCoreConceptKey> deserializer() {
        return DESERIALIZER;
    }

}
