package org.openwebinars.course.gettingStarted;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

import java.util.Collections;

public class ExampleConfigSourceProvider implements ConfigSourceProvider {
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader classLoader) {
        return Collections.singletonList(new InMemoryConfigSource());
    }
}
