package org.openwebinars.course.gettingStarted;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.concurrent.CompletionStage;

@Path("/api")
@RegisterRestClient()
public interface WorldClockService {
    @GET
    @Path("/json/cet/now")
    @Produces(MediaType.APPLICATION_JSON)
    WorldClock getCurrentDateTime();

    @GET
    @Path("/json/cet/now")
    @Produces(MediaType.APPLICATION_JSON)
    WorldClock getCurrentDateTimeWithHeaders(@BeanParam WorldClockHeaders worldClockHeaders);

    @GET
    @Path("/json/{timeZone}/now")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<WorldClock> getCurrentDateTimeByTimeZone(@PathParam("timeZone") String timeZone);
}
