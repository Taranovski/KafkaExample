package com.dark.future.kafka_simple.stream.serde;

import com.dark.future.kafka_simple.stream.data.ResultingActions;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class ResultingActionSerde implements Serde<ResultingActions> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<ResultingActions> serializer() {
        return null;
    }

    @Override
    public Deserializer<ResultingActions> deserializer() {
        return null;
    }
}
