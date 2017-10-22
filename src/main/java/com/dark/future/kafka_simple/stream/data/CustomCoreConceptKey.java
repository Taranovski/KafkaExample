package com.dark.future.kafka_simple.stream.data;

import java.util.Objects;

/**
 * Created by Titan on 18.10.2017.
 */
public class CustomCoreConceptKey {
    private final String coreConcept;
    private final String instanceId;

    public CustomCoreConceptKey(String coreConcept, String instanceId) {
        this.coreConcept = coreConcept;
        this.instanceId = instanceId;
    }

    public String getCoreConcept() {
        return coreConcept;
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomCoreConceptKey that = (CustomCoreConceptKey) o;
        return Objects.equals(coreConcept, that.coreConcept) &&
                Objects.equals(instanceId, that.instanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coreConcept, instanceId);
    }
}
