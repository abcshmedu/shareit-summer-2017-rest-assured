package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import edu.hm.shareit.storage.MediaStorageHibernate;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hm.shareit.api.MediaResource;
import edu.hm.shareit.api.UserResource;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.User;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceImpl;
import edu.hm.shareit.service.MediaServiceResult;

/**
 * MediaResourceTestTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class MediaResourceTest {

    private final Book friedhof = new Book("Friedhof der Kuscheltiere", "Stephen King", "9783453435797");
    private final Book achtnacht = new Book("AchtNacht", "Sebastian Fitzek", "9783426521083");
    private final Disc disc1 = new Disc("Disc Title", "7501031311309", "Director", 0);
    private final Disc disc2 = new Disc("Other Disc", "7501054530107", "Other Director", 6);

    private final MediaService mediaService = new MediaServiceImpl(new MediaStorageHibernate());
    
    private final String token = new UserResource().login(new User("bob", "42")).getEntity().toString();

    /**
     * Test addBook method.
     */
    @Test
    public void addBookTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        Response response = mediaResource.addBook(friedhof, token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
    }

    /**
     * Test getBooks method with empty mediaResource.
     */
    @Test
    public void getBooksEmptyTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        Response response = mediaResource.getBooks(token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(response.getEntity(), "[]");
    }

    /**
     * Test getBooks method.
     * @throws JsonProcessingException JsonProcessingException
     */
    @Test
    public void getBooksTest() throws JsonProcessingException {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addBook(friedhof, token);
        mediaResource.addBook(achtnacht, token);
        ObjectMapper mapper = new ObjectMapper();
        String json = "[" + mapper.writeValueAsString(friedhof) + "," + mapper.writeValueAsString(achtnacht) + "]";
        Response response = mediaResource.getBooks(token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test getBook method.
     * @throws JsonProcessingException JsonProcessingException
     */
    @Test
    public void getBookTest() throws JsonProcessingException {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addBook(friedhof, token);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(friedhof);
        Response response = mediaResource.getBook(friedhof.getIsbn(), token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test getBook method with non existing isbn.
     */
    @Test
    public void getBookNotAvailableTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        String json = MediaServiceResult.ISBN_NOT_FOUND.getStatus();
        Response response = mediaResource.getBook(friedhof.getIsbn(), token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test updateBook method with isbn mismatch.
     */
    @Test
    public void updateBookIsbnMismatchTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addBook(friedhof, token);
        Response response = mediaResource.updateBook("978-3426199190", achtnacht, token);
        assertEquals(MediaServiceResult.ISBN_MISMATCH.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.ISBN_MISMATCH.getStatus(), response.getEntity());
    }

    /**
     * Test updateBook method.
     */
    @Test
    public void updateBookTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addBook(friedhof, token);
        Response response = mediaResource.updateBook(friedhof.getIsbn(), new Book("Neuer Titel", "Stephen King", friedhof.getIsbn()), token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.OK.getStatus(), response.getEntity());
    }


    //Discs

    /**
     * Test addDisc method.
     */
    @Test
    public void addDiscTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        Response response = mediaResource.addDisc(disc1, token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
    }

    /**
     * Test getDiscs method for empty mediaResource.
     */
    @Test
    public void getDiscsEmptyTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        Response response = mediaResource.getDiscs(token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(response.getEntity(), "[]");
    }

    /**
     * Test getDiscs method.
     * @throws JsonProcessingException JsonProcessingException
     */
    @Test
    public void getDiscsTest() throws JsonProcessingException {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addDisc(disc1, token);
        mediaResource.addDisc(disc2, token);
        ObjectMapper mapper = new ObjectMapper();
        String json = "[" + mapper.writeValueAsString(disc1) + "," + mapper.writeValueAsString(disc2) + "]";
        Response response = mediaResource.getDiscs(token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test getDisc method.
     * @throws JsonProcessingException JsonProcessingException
     */
    @Test
    public void getDiscTest() throws JsonProcessingException {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addDisc(disc1, token);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(disc1);
        Response response = mediaResource.getDisc(disc1.getBarcode(), token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test getDisc method with non existing barcode.
     */
    @Test
    public void getDiscNotAvailableTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        String json = MediaServiceResult.BARCODE_NOT_FOUND.getStatus();
        Response response = mediaResource.getDisc(disc1.getBarcode(), token);
        assertEquals(json, response.getEntity());
    }

    /**
     * Test updateDisc method with barcode mismatch.
     */
    @Test
    public void updateDiscBarcodeMismatchTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addDisc(disc1, token);
        Response response = mediaResource.updateDisc(disc1.getBarcode(), disc2, token);
        assertEquals(MediaServiceResult.BARCODE_MISMATCH.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.BARCODE_MISMATCH.getStatus(), response.getEntity());
    }

    /**
     * Test updateDisc method.
     */
    @Test
    public void updateDiscTest() {
        MediaResource mediaResource = new MediaResource(mediaService);
        mediaResource.addDisc(disc1, token);
        Response response = mediaResource.updateDisc(disc1.getBarcode(), new Disc("Updated Title", disc1.getBarcode(), "New Director", 0), token);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.OK.getStatus(), response.getEntity());
    }
}
