package com.dark.future.kafka_simple.stream.serde;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Titan on 20.10.2017.
 */
public class CustomCoreConceptKeySerdeTest {

    private CustomCoreConceptKeySerde customCoreConceptKeySerde = new CustomCoreConceptKeySerde();

    @Test
    public void shouldSerializeAndDeserializeFullObjectProperly() {
        CustomCoreConceptKey customCoreConceptKey = new CustomCoreConceptKey("abc", "cdef");

        byte[] bytes = customCoreConceptKeySerde.serializer().serialize("topic", customCoreConceptKey);

        CustomCoreConceptKey deserialized = customCoreConceptKeySerde.deserializer().deserialize("topic", bytes);

        assertEquals(customCoreConceptKey, deserialized);
    }

    @Test
    public void shouldSerializeAndDeserializePartialObjectProperly1() {
        CustomCoreConceptKey customCoreConceptKey = new CustomCoreConceptKey("abc", null);

        byte[] bytes = customCoreConceptKeySerde.serializer().serialize("topic", customCoreConceptKey);

        CustomCoreConceptKey deserialized = customCoreConceptKeySerde.deserializer().deserialize("topic", bytes);

        assertEquals(customCoreConceptKey, deserialized);
    }
    @Test
    public void shouldSerializeAndDeserializePartialObjectProperly2() {
        CustomCoreConceptKey customCoreConceptKey = new CustomCoreConceptKey(null, "abc");

        byte[] bytes = customCoreConceptKeySerde.serializer().serialize("topic", customCoreConceptKey);

        CustomCoreConceptKey deserialized = customCoreConceptKeySerde.deserializer().deserialize("topic", bytes);

        assertEquals(customCoreConceptKey, deserialized);
    }
    @Test
    public void shouldSerializeAndDeserializeEmptyObjectProperly() {
        CustomCoreConceptKey customCoreConceptKey = new CustomCoreConceptKey(null, null);

        byte[] bytes = customCoreConceptKeySerde.serializer().serialize("topic", customCoreConceptKey);

        CustomCoreConceptKey deserialized = customCoreConceptKeySerde.deserializer().deserialize("topic", bytes);

        assertEquals(customCoreConceptKey, deserialized);
    }
}