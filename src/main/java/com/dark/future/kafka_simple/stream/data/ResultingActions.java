package com.dark.future.kafka_simple.stream.data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Titan on 18.10.2017.
 */
public class ResultingActions {
    private final Set<String> messages = new ConcurrentSkipListSet<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public Set<String> getMessages() {
        return new HashSet<>(messages);
    }


    @Override
    public String toString() {
        return "ResultingActions{" +
                "messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultingActions that = (ResultingActions) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }
}
