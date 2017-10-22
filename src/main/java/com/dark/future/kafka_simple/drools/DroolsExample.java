package com.dark.future.kafka_simple.drools;

import org.drools.core.RuleBaseConfiguration;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Titan on 13.10.2017.
 */
public class DroolsExample {
    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();

        KieBase kieBase = kContainer.newKieBase(RuleBaseConfiguration.getDefaultInstance());

//        kieBase.


        KieSession kSession = kContainer.newKieSession();
        kSession.setGlobal("out", System.out);
        kSession.insert(new Message("Dave", "Hello, HAL. Do you read me, HAL?"));
        kSession.fireAllRules();
    }
}
