package com.dark.future.kafka_simple.stream;

import com.dark.future.kafka_simple.sender.Sender;
import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import com.dark.future.kafka_simple.stream.data.ResultingActions;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.Windowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Titan on 18.10.2017.
 */
@Component
public class FireBatchUpdate implements ForeachAction<Windowed<CustomCoreConceptKey>, ResultingActions> {

    @Autowired
    private Sender sender;

    @Value("${kafka.topic.helloworld4}")
    private String destination;

    @Override
    public void apply(Windowed<CustomCoreConceptKey> key, ResultingActions value) {
        sender.send(destination, value.toString());
    }
}
