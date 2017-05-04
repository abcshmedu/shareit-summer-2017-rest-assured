/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 18/04/2017
 */
@Path("/media")
public class Api {

    @GET
    @Path("/books")
    @Produces("application/json")
    public Response foo() {
        return Response.status(Response.Status.OK).build();
    }

}
