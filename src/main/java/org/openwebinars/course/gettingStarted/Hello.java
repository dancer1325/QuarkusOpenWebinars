package org.openwebinars.course.gettingStarted;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
import java.util.List;
import java.util.stream.Collectors;


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

    @Inject
    DeveloperWithPanacheRepository developerWithPanacheRepository;

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

    @POST()
    @Path("/developerWithPanache")
    @Transactional      // Required since we are creating it
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewDeveloperWithPanache(DeveloperWithPanache developer) {
        System.out.println(developer);
        developer.persist();
        return Response.created(URI.create("/developer" + developer.id)).build();
    }

    @GET()
    @Path("/developerWithPanache/{id}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public DeveloperWithPanache getDeveloperWithPanache(@PathParam("id") Long id) {
        // By default, the id in Panache is a Long
        System.out.println(id);
        return DeveloperWithPanache.findById(id);
    }

    @GET()
    @Path("/developerWithPanache/allAsEntityBase")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public List<PanacheEntityBase> getAllDevelopersAsEntityBaseWithPanache() {
        return DeveloperWithPanache.findAll().stream().collect(Collectors.toList());
    }

    @GET()
    @Path("/developerWithPanache/all")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public List<DeveloperWithPanache> getAllDevelopersWithPanache() {
        return DeveloperWithPanache.findAll().list();
    }

    @GET()
    @Path("/developerWithPanache/name/{name}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public DeveloperWithPanache getDeveloperWithPanacheByName(@PathParam("name") String name) {
        // By default, the id in Panache is a Long
        System.out.println(name);
        return DeveloperWithPanache.find("name", name).firstResult();
    }

    // TODO: Fix it to make it work
    @GET()
    @Path("/developerWithPanache/nameJQL/{name}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public DeveloperWithPanache getDeveloperWithPanacheByNameViaJQL(@PathParam("name") String name) {
        // By default, the id in Panache is a Long
        System.out.println(name);
        return DeveloperWithPanache.find("SELECT * FROM 'some-mariadb' WHERE name=?", name).firstResult();
    }

    @GET()
    @Path("/developerWithPanache/name/{name}/age/{age}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public DeveloperWithPanache getDeveloperWithPanacheByNameAndAge(@PathParam("name") String name, @PathParam("age") Integer age) {
        // By default, the id in Panache is a Long
        System.out.println(name);
        return DeveloperWithPanache.find("name = ?1 and age = ?2", name, age).firstResult();
    }

    // TODO: Fix it to make it work
    @GET()
    @Path("/developerWithPanacheViaDAO/name/{name}")
    @Transactional      // Required since we are creating it
    @Produces(MediaType.APPLICATION_JSON)
    public Developer getDeveloperWithPanacheByNameViaDAO(@PathParam("name") String name) {
        System.out.println(name);
        return developerWithPanacheRepository.findByName(name);
    }

    // TODO: Fix it to make it work
    @POST()
    @Path("/developerWithPanacheViaDAO")
    @Transactional      // Required since we are creating it
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewDeveloperWithPanacheViaDAO(Developer developer) {
        System.out.println(developer);
        developerWithPanacheRepository.create(developer);
        return Response.created(URI.create("/developer" + developer.getId())).build();
    }

}
