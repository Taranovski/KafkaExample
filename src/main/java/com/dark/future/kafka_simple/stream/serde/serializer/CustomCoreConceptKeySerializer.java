package com.dark.future.kafka_simple.stream.serde.serializer;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class CustomCoreConceptKeySerializer implements Serializer<CustomCoreConceptKey> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, CustomCoreConceptKey data) {
        byte[] bytes1 = data.getCoreConcept() == null ? new byte[0] : data.getCoreConcept().getBytes();
        byte[] bytes2 = data.getInstanceId() == null ? new byte[0] : data.getInstanceId().getBytes();

        byte[] bytes = new byte[bytes1.length + bytes2.length + 8];

        System.arraycopy(ByteBuffer.allocate(4).putInt(bytes1.length).array(), 0, bytes, 0, 4);
        System.arraycopy(bytes1, 0, bytes, 4, bytes1.length);
        System.arraycopy(ByteBuffer.allocate(4).putInt(bytes2.length).array(), 0, bytes, 4 + bytes1.length, 4);
        System.arraycopy(bytes2, 0, bytes, bytes1.length + 8, bytes2.length);

        return bytes;
    }

    @Override
    public void close() {

    }
}
