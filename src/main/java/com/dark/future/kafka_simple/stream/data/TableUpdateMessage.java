package com.dark.future.kafka_simple.stream.data;

/**
 * Created by Titan on 18.10.2017.
 */
public class TableUpdateMessage {
    private final String message;

    public TableUpdateMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
