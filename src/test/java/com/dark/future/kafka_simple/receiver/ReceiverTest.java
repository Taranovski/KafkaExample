package com.dark.future.kafka_simple.receiver;

import com.dark.future.kafka_simple.JMSTestUtilsAsyncAOPConfig;
import com.dark.future.kafka_simple.JMSTopicsConfig;
import com.dark.future.kafka_simple.config.ReceiverConfig;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by Titan on 29.09.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Receiver.class,
        ReceiverConfig.class,
        JMSTestUtilsAsyncAOPConfig.class
})
@DirtiesContext
public class ReceiverTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverTest.class);
    public static final String HELLOWORLD_TOPIC = JMSTopicsConfig.HELLOWORLD_TOPIC;

    @Autowired
    private Receiver receiver;

    @Autowired
    private JMSTestUtilsAsyncAOPConfig.MessageEndAspect messageEndAspect;

    private KafkaTemplate<String, String> template;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

    @Before
    public void setUp() throws Exception {
        // set up the Kafka producer properties
        Map<String, Object> senderProperties =
                KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());

        // create a Kafka producer factory
        ProducerFactory<String, String> producerFactory =
                new DefaultKafkaProducerFactory<String, String>(senderProperties);

        // create a Kafka template
        template = new KafkaTemplate<>(producerFactory);
        // set the default topic to send to
        template.setDefaultTopic(HELLOWORLD_TOPIC);

        // wait until the partitions are assigned
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    embeddedKafka.getPartitionsPerTopic());
        }

        messageEndAspect.setRunnable(null);
    }

    @Test
    public void testReceive() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        messageEndAspect.setRunnable(countDownLatch::countDown);

        // send the message
        String greeting = "Hello Spring Kafka Receiver!";
        template.sendDefault(greeting);
        LOGGER.debug("test-sender sent message='{}'", greeting);

        countDownLatch.await();

        assertEquals(0, countDownLatch.getCount());
    }
}