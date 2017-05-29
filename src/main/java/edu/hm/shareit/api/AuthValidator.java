/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.ws.rs.core.Response;

/**
 * Check validity of jwt token with auth service.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 29.05.17
 */
public final class AuthValidator {

    /**
     * URL for checking jet validity with auth service.
     */
    private static final String URL_AUTH_VALIDATE = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/validate";

    /**
     * Check validity of jwt token with auth service.
     *
     * @param jwt jwt token to check.
     * @return true if token is valid, false otherwise.
     */
    public static boolean checkValidity(String jwt) {
        try {
            final int status = Unirest.get(URL_AUTH_VALIDATE + "/" + jwt).asBinary().getStatus();
            return status == Response.Status.OK.getStatusCode();
        } catch (UnirestException e) {
            return false;
        }
    }

    /**
     * Hidden ctor.
     */
    private AuthValidator() {

    }

}
