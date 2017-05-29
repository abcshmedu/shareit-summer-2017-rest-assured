/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 29.05.17
 */
@Path("/user")
public class UserResource {

    private static final String URL_AUTH_LOGIN = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/login";
    private static final String URL_AUTH_LOGOUT = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/logout";

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String user) {
        try {
            final HttpResponse<String> response = Unirest.post(URL_AUTH_LOGIN).body(user).asString();
            if (response.getStatus() == 200) {
                return Response.status(200).entity(response.getBody()).build();
            }
        } catch (UnirestException ignored) {
        }
        return Response.status(401).build();
    }

    @GET
    @Path("/logout/{username}/{jwt}")
    public Response logout(@PathParam("username") String username, @PathParam("jwt") String jwt) {
        try {
            final HttpResponse<InputStream> response = Unirest.get(URL_AUTH_LOGOUT + "/" + username + "/" + jwt).asBinary();
            if (response.getStatus() == 200) {
                return Response.status(200).build();
            }
        } catch (UnirestException ignored) {
        }
        return Response.status(401).build();
    }

}
