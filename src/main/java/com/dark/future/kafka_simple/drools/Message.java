package com.dark.future.kafka_simple.drools;

/**
 * Created by Titan on 13.10.2017.
 */
public class Message {
    private final String dave;
    private final String s;

    public Message(String dave, String s) {
        this.dave = dave;
        this.s = s;
    }

    public String getDave() {
        return dave;
    }

    public String getS() {
        return s;
    }
}
