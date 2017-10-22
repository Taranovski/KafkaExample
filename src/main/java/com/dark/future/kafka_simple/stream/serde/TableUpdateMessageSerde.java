package com.dark.future.kafka_simple.stream.serde;

import com.dark.future.kafka_simple.stream.data.TableUpdateMessage;
import com.dark.future.kafka_simple.stream.serde.deserializer.TableUpdateMessageDeserializer;
import com.dark.future.kafka_simple.stream.serde.serializer.TableUpdateMessageSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
@Component
public class TableUpdateMessageSerde implements Serde<TableUpdateMessage> {

    public static final Serializer<TableUpdateMessage> SERIALIZER = new TableUpdateMessageSerializer();
    public static final Deserializer<TableUpdateMessage> DESERIALIZER = new TableUpdateMessageDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<TableUpdateMessage> serializer() {
        return SERIALIZER;
    }

    @Override
    public Deserializer<TableUpdateMessage> deserializer() {
        return DESERIALIZER;
    }

}
