package org.openwebinars.course.gettingStarted;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

    @Path("/beer")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Beer beer() {
//        return "Hello from RESTEasy Reactive";
        //return messageConfigured;
        return new Beer("Alfredos", 300);
    }

    // MediaType.APPLICATION_JSON       It's the default one for POST
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBeer(@Valid Beer beer) {
        System.out.println(beer);
        return Response.ok().build();
    }
}
