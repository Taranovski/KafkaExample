package com.dark.future.kafka_simple.stream.serde.deserializer;

import com.dark.future.kafka_simple.stream.data.TableUpdateMessage;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class TableUpdateMessageDeserializer implements Deserializer<TableUpdateMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public TableUpdateMessage deserialize(String topic, byte[] data) {
        return new TableUpdateMessage(new String(data));
    }

    @Override
    public void close() {

    }
}
