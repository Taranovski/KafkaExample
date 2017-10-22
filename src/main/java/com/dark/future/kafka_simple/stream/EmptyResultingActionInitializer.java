package com.dark.future.kafka_simple.stream;

import com.dark.future.kafka_simple.stream.data.ResultingActions;
import org.apache.kafka.streams.kstream.Initializer;

/**
 * Created by Titan on 18.10.2017.
 */
public class EmptyResultingActionInitializer implements Initializer<ResultingActions> {
    @Override
    public ResultingActions apply() {
        return new ResultingActions();
    }
}
