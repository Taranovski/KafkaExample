package com.dark.future.kafka_simple.stream;

import com.dark.future.kafka_simple.stream.data.CustomCoreConceptKey;
import com.dark.future.kafka_simple.stream.data.ResultingActions;
import com.dark.future.kafka_simple.stream.data.TableUpdateMessage;
import com.dark.future.kafka_simple.stream.serde.CustomCoreConceptKeySerde;
import com.dark.future.kafka_simple.stream.serde.ReadFromInitialMessageCustomCoreConceptKeySerde;
import com.dark.future.kafka_simple.stream.serde.ResultingActionSerde;
import com.dark.future.kafka_simple.stream.serde.TableUpdateMessageSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Titan on 18.10.2017.
 */
@Service
public class StreamExample {

    private ReadFromInitialMessageCustomCoreConceptKeySerde keySerde;
    private TableUpdateMessageSerde valSerde;
    private EmptyResultingActionInitializer initializer;
    private ResultingActionAggregator aggregator;
    private List<String> topicsList;
    private TimeWindows milliseconds;
    private Properties config;
    private CustomCoreConceptKeySerde keySerde1;
    private ResultingActionSerde aggValueSerde;
    private FireBatchUpdate action;


    @Value("${kafka.bootstrap-servers}")
    private String broker;

    public StreamExample(FireBatchUpdate action) {

        this.action = action;
    }


    @PostConstruct
    public void runStreamProcessing() {
        topicsList = Arrays.asList(
                "hello_world_topic1",
                "hello_world_topic2",
                "hello_world_topic3"
        );

        KStreamBuilder kStreamBuilder = new KStreamBuilder();

        KStream<String, String> kStream = kStreamBuilder.stream(
                getTopics(topicsList)
        );

        kStream.through("hello_world_topic4");

        config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 500);
        config.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        config.put(StreamsConfig.STATE_DIR_CONFIG, "c:\\dev\\1");

        KafkaStreams streams = new KafkaStreams(kStreamBuilder, config);
        streams.start();
        System.out.println("started kafka streams");



//        keySerde = new ReadFromInitialMessageCustomCoreConceptKeySerde();
//        valSerde = new TableUpdateMessageSerde();
//        initializer = new EmptyResultingActionInitializer();
//        aggregator = new ResultingActionAggregator();
//
//        topicsList = Arrays.asList(
//                "hello_world_topic1",
//                "hello_world_topic2",
//                "hello_world_topic3"
//        );
//
//        milliseconds = TimeWindows.of("milliseconds", 1000);
//
//        config = new Properties();
//        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
//        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
//        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);
//        config.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
////        config.put("linger.ms", 5);
//        keySerde1 = new CustomCoreConceptKeySerde();
//        aggValueSerde = new ResultingActionSerde();
//
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//
//        KStream<CustomCoreConceptKey, TableUpdateMessage> kStream = kStreamBuilder.stream(
//                keySerde, valSerde,
//                getTopics(topicsList)
//        );
//
//        KTable<Windowed<CustomCoreConceptKey>, ResultingActions> windowedResultingActionKTable = kStream.aggregateByKey(
//                initializer,
//                aggregator,
//                milliseconds,
//                keySerde1,
//                aggValueSerde
//        );
//
//        windowedResultingActionKTable.toStream().to("hello_world_topic4");
//
//        KafkaStreams streams = new KafkaStreams(kStreamBuilder, config);
//        streams.start();
    }

    private String[] getTopics(List<String> topicsList) {
        return topicsList.toArray(new String[]{});
    }
}
