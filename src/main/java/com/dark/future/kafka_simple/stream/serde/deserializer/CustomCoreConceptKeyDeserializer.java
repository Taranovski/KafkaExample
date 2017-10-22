package com.dark.future.kafka_simple.stream.serde.deserializer;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Created by Titan on 18.10.2017.
 */
public class CustomCoreConceptKeyDeserializer implements Deserializer<CustomCoreConceptKey> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public CustomCoreConceptKey deserialize(String topic, byte[] data) {
        String first = null;
        String second = null;

        byte[] length1 = new byte[4];
        byte[] length2 = new byte[4];

        System.arraycopy(data, 0, length1, 0, 4);

        int lengthInt1 = ByteBuffer.wrap(length1).getInt();
        if (lengthInt1 > 0) {
            byte[] firstField = new byte[lengthInt1];
            System.arraycopy(data, 4, firstField, 0, lengthInt1);
            first = new String(firstField);

            System.arraycopy(data, 4 + lengthInt1, length2, 0, 4);

            int lengthInt2 = ByteBuffer.wrap(length2).getInt();
            if (lengthInt2 > 0) {
                byte[] secondField = new byte[lengthInt2];

                System.arraycopy(data, 8 + lengthInt1, secondField, 0, lengthInt2);

                second = new String(secondField);
            }
        } else {
            System.arraycopy(data, 4 + lengthInt1, length2, 0, 4);

            int lengthInt2 = ByteBuffer.wrap(length2).getInt();
            if (lengthInt2 > 0) {
                byte[] secondField = new byte[lengthInt2];

                System.arraycopy(data, 8, secondField, 0, lengthInt2);

                second = new String(secondField);
            }

        }


        return new CustomCoreConceptKey(first, second);
    }

    @Override
    public void close() {

    }
}
