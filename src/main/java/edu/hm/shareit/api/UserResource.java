/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.hm.shareit.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * REST API for login and logout.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 29.05.17
 */
@Path("/user")
public class UserResource {

    /**
     * URL for querying auth service for login.
     */
    private static final String URL_AUTH_LOGIN = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/login";
    /**
     * URL for querying auth service for logout.
     */
    private static final String URL_AUTH_LOGOUT = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/logout";

    /**
     * Initialize ObjectMapper for Unirest according to website.
     */
    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Login API Call. Will be delegated to auth service.
     *
     * @param user User Object containing username and password.
     * @return Response according to result from auth service.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try {
            final HttpResponse<String> response = Unirest.post(URL_AUTH_LOGIN)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(user)
                    .asString();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return Response.status(Response.Status.OK.getStatusCode()).entity(response.getBody()).build();
            }
        } catch (UnirestException ignored) {
        }
        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build();
    }

    /**
     * Logout API Call. Will be delegated to auth service.
     *
     * @param username Username.
     * @param jwt      The user's jwt token.
     * @return Response according to result from auth service.
     */
    @GET
    @Path("/logout/{username}/{jwt}")
    public Response logout(@PathParam("username") String username, @PathParam("jwt") String jwt) {
        try {
            final HttpResponse<InputStream> response = Unirest.get(URL_AUTH_LOGOUT + "/" + username + "/" + jwt).asBinary();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return Response.status(Response.Status.OK.getStatusCode()).build();
            }
        } catch (UnirestException ignored) {
        }
        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build();
    }

}
