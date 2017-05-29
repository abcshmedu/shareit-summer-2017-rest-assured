/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 29.05.17
 */
public class AuthValidator {

    private static final String URL_AUTH_VALIDATE = "https://shareit-auth-rest-assured.herokuapp.com/shareit/auth/validate";

    public static boolean checkValidity(String jwt) {
        try {
            final int status = Unirest.get(URL_AUTH_VALIDATE + "/" + jwt).asBinary().getStatus();
            return status == 200;
        } catch (UnirestException e) {
            return false;
        }
    }

}
