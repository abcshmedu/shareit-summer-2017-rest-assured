package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import edu.hm.shareit.api.UserResource;
import edu.hm.shareit.model.User;

/**
 * Tests for UserResource.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class UserResourceTest {

    private final int ok = 200;
    private final int unauthorized = 401;
    
    /**
     * Test login with valid token.
     */
    @Test
    public void loginValidTest() {
        final Response res = new UserResource().login(new User("bob", "42"));
        assertEquals(ok, res.getStatus());
    }

    /**
     * Test login with invalid token.
     */
    @Test
    public void loginInvalidTest() {
        final Response res = new UserResource().login(new User("horst", "42"));
        assertEquals(unauthorized, res.getStatus());
    }

    /**
     * Test logout with valid parameters.
     */
    @Test
    public void logoutValidTest() {
        UserResource resource = new UserResource();
        String token = resource.login(new User("bob", "42")).getEntity().toString();
        Response res = resource.logout("bob", token);
        assertEquals(ok, res.getStatus());
    }

    /**
     * Test logout with invalid user.
     */
    @Test
    public void logoutInvalidTest() {
        UserResource resource = new UserResource();
        String token = resource.login(new User("bob", "42")).getEntity().toString();
        Response res = resource.logout("horst", token);
        assertEquals(unauthorized, res.getStatus());
    }
}
