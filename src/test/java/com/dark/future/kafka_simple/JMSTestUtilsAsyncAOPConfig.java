package com.dark.future.kafka_simple;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by Titan on 29.09.2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class JMSTestUtilsAsyncAOPConfig {

    @Aspect
    @Component
    public static class MessageStartAspect {

        private Runnable runnable;

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @org.aspectj.lang.annotation.After("within(com.dark.future.kafka_simple.sender.Sender)")
        public void after() {
            if (runnable != null) runnable.run();
        }

    }

    @Aspect
    @Component
    public static class MessageEndAspect {

        private Runnable runnable;

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @org.aspectj.lang.annotation.After("within(com.dark.future.kafka_simple.receiver.Receiver)")
        public void after() {
            if (runnable != null) runnable.run();
        }

    }

}
