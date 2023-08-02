package org.openwebinars.course.gettingStarted;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.net.URI;


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

    @Inject
    ExpensiveService expensiveService;

    @Inject
    EntityManager entityManager;

    Logger logger = Logger.getLogger(Hello.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.debug("/hello");
//        return "Hello from RESTEasy Reactive";
        //return messageConfigured;
        return helloService.toUpperCase(messageConfigured);
    }

    @GET
    @Path("/calculate")
    @Produces(MediaType.TEXT_PLAIN)
    public int calculate() {
        logger.debug("/calculate");
        return expensiveService.calculate();
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

    // Via JAXWS specification
    // It could return a timeOut
    @Path("/currentDateTimeWithJAXRS")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public WorldClock getCurrentDateTimeViaJAXWS() {
        return ClientBuilder.newClient()
                .target("http://worldclockapi.com")
                .path("/api/json/cet/now")
                .request()
                .get(WorldClock.class);
    }

    @POST()
    @Path("/developer")
    @Transactional      // Required since we are creating it
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewDeveloper(Developer developer) {
        System.out.println(developer);
        entityManager.persist(developer);
        return Response.created(URI.create("/developer" + developer.getId())).build();
    }

    @GET()
    @Path("/developer/{id}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public Developer getDeveloper(@PathParam("id") Integer id) {
        System.out.println(id);
        return entityManager.find(Developer.class, id);
    }

}
