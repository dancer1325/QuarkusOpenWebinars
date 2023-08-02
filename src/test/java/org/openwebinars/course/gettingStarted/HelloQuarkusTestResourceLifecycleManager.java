package org.openwebinars.course.gettingStarted;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.HashMap;
import java.util.Map;

public class HelloQuarkusTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {
    @Override
    public Map<String, String> start() {
        System.out.println("Time to start the test execution");
        Map<String, String> configuration = new HashMap<>();
        configuration.put("greetings.message", "Testing hello");
        return configuration;
    }

    @Override
    public void stop() {
        System.out.println("Tests already executed");
    }
}
