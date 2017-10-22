package com.dark.future.kafka_simple.stream;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import com.dark.future.kafka_simple.stream.data.ResultingActions;
import com.dark.future.kafka_simple.stream.data.TableUpdateMessage;
import org.apache.kafka.streams.kstream.Aggregator;

/**
 * Created by Titan on 18.10.2017.
 */
public class ResultingActionAggregator implements Aggregator<CustomCoreConceptKey, TableUpdateMessage, ResultingActions> {

    @Override
    public ResultingActions apply(CustomCoreConceptKey aggKey, TableUpdateMessage value, ResultingActions aggregate) {
        aggregate.addMessage(value.getMessage());
        return aggregate;
    }
}
