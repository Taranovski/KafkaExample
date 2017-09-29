package com.dark.future.kafka_simple;

import com.dark.future.kafka_simple.sender.Sender;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JMSTestUtilsAsyncAOPConfig.class,
        KafkaSimpleApplication.class
})

public class KafkaSimpleApplicationTests {

    public static final String HELLOWORLD_TOPIC = JMSTopicsConfig.HELLOWORLD_TOPIC;
    @Test
    public void contextLoads() {
    }

    @Autowired
    private Sender sender;

    @Autowired
    private JMSTestUtilsAsyncAOPConfig.MessageStartAspect messageStartAspect;

    @Autowired
    private JMSTestUtilsAsyncAOPConfig.MessageEndAspect messageEndAspect;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

    @Before
    public void setUp() throws Exception {
        // wait until the partitions are assigned
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    embeddedKafka.getPartitionsPerTopic());
        }

        messageStartAspect.setRunnable(null);
        messageEndAspect.setRunnable(null);
    }

    @Test(timeout = 3000)
    public void testReceive() throws Exception {
        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        messageStartAspect.setRunnable(() -> {
            try {
                countDownLatch2.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        messageEndAspect.setRunnable(new Thread(countDownLatch2::countDown)::start);

        sender.send(HELLOWORLD_TOPIC, "Hello Spring Kafka!");

        assertEquals(0, countDownLatch2.getCount());
    }

}
