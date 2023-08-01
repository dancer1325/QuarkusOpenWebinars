package org.openwebinars.course.gettingStarted;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

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
}
