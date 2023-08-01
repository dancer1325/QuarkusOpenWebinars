package org.openwebinars.course.gettingStarted;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1")
@RegisterRestClient()
public interface OpenMeteoService {

    @GET
    @Path("/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m")
    @Produces(MediaType.APPLICATION_JSON)
    OpenMeteo getCurrentOpenMeteo();
}
