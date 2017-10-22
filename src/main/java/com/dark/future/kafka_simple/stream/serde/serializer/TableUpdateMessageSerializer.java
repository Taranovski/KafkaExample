package com.dark.future.kafka_simple.stream.serde.serializer;

import com.dark.future.kafka_simple.stream.data.TableUpdateMessage;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
@Component
public class TableUpdateMessageSerializer implements Serializer<TableUpdateMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, TableUpdateMessage data) {
        return data.getMessage().getBytes();
    }

    @Override
    public void close() {

    }
}
