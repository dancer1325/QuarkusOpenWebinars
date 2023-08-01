package org.openwebinars.course.gettingStarted;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String toUpperCase(String message) {
        return message.toUpperCase();
    }
}
