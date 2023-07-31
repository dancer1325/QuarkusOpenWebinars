package org.openwebinars.course.gettingStarted;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO: What's missing to make it work
public class InMemoryConfigSource implements ConfigSource {

    private Map<String, String> properties = new HashMap<>();

    public InMemoryConfigSource() {
        this.properties.put("greetings.message", "Memory hello spi");
    }
    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public String getValue(String s) {
        return properties.get(s);
    }

    @Override
    public String getName() {
        return "InMemoryConfigSource";
    }

    // Higher ordinal -> higher preference
    @Override
    public int getOrdinal() {
        return 900;
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

}
