package org.openwebinars.course.gettingStarted;

import jakarta.ws.rs.HeaderParam;

public class WorldClockHeaders {
    @HeaderParam("X-Logger")
    String logger;
}
