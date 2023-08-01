package org.openwebinars.course.gettingStarted;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class Hello {

    @ConfigProperty(name = "greetings.message")
    String messageConfigured;

    @Inject
    HelloService helloService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
//        return "Hello from RESTEasy Reactive";
        //return messageConfigured;
        return helloService.toUpperCase(messageConfigured);
    }
}
