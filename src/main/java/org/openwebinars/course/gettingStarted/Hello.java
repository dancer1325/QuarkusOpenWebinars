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
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Path("/hello")
public class Hello {

    @ConfigProperty(name = "greetings.message")
    String messageConfigured;

    @Inject
    HelloService helloService;

    @Inject
    @RestClient
    OpenMeteoService openMeteoService;

    @Inject
    @RestClient
    WorldClockService worldClockService;

    Logger logger = Logger.getLogger(Hello.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.debug("/hello");
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

    // TODO: Fix why it's not working
    @Path("/currentTemperature")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public OpenMeteo getCurrentTemperature() {
        return openMeteoService.getCurrentOpenMeteo();
    }

    // It could return a timeOut
    @Path("/currentDateTime")
    @GET()
    // Set a static header
    //@ClientHeaderParam(name="X-Logger", value = "DEBUG")
    @Produces(MediaType.APPLICATION_JSON)
    public WorldClock getCurrentDateTime() {
        return worldClockService.getCurrentDateTime();
    }

    // It could return a timeOut
    @Path("/currentDateTimeWithHeaders")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public WorldClock getCurrentDateTimeViaHeaders() {
        WorldClockHeaders worldClockHeaders = new WorldClockHeaders();
        worldClockHeaders.logger="DEBUG";
        return worldClockService.getCurrentDateTimeWithHeaders(worldClockHeaders);
    }

}
