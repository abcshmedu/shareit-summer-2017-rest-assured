package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hm.shareit.api.MediaResource;
import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.service.MediaServiceResult;

public class MediaResourceTest {

	private final Book friedhof = new Book("Friedhof der Kuscheltiere", "Stephen King", "978-3453435797");
	private final Book achtnacht = new Book("AchtNacht", "Sebastian Fitzek", "978-3426521083");
	private final Disc disc1 = new Disc("Disc Title", "7501031311309", "Director", 0);
	private final Disc disc2 = new Disc("Other Disc", "7501054530107", "Other Director", 6);
	
	@Test
    public void addBookTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        Response response = mediaResource.addBook(friedhof);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
    }
	
	@Test
    public void getBooksEmptyTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        Response response = mediaResource.getBooks();
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(response.getEntity(), "[]");
    }
	
	@Test
    public void getBooksTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addBook(friedhof);
        mediaResource.addBook(achtnacht);
        ObjectMapper mapper = new ObjectMapper();
        String json = "[" + mapper.writeValueAsString(friedhof) + "," + mapper.writeValueAsString(achtnacht) + "]";
        Response response = mediaResource.getBooks();
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void getBookTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addBook(friedhof);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(friedhof);
        Response response = mediaResource.getBook(friedhof.getIsbn());
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void getBookNotAvailableTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        String json = MediaServiceResult.ISBN_NOT_FOUND.getStatus();
        Response response = mediaResource.getBook(friedhof.getIsbn());
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void updateBookIsbnMismatchTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addBook(friedhof);
        Response response = mediaResource.updateBook("978-3426199190", achtnacht);
        assertEquals(MediaServiceResult.ISBN_MISMATCH.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.ISBN_MISMATCH.getStatus(), response.getEntity());
    }
	
	@Test
	public void updateBookTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addBook(friedhof);
        Response response = mediaResource.updateBook(friedhof.getIsbn(), new Book("Neuer Titel", "Stephen King", friedhof.getIsbn()));
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.OK.getStatus(), response.getEntity());
    }
	
	
	//Discs
	
	@Test
    public void addDiscTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        Response response = mediaResource.addDisc(disc1);
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
    }
	
	@Test
    public void getDiscsEmptyTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        Response response = mediaResource.getDiscs();
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(response.getEntity(), "[]");
    }
	
	@Test
    public void getDiscsTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addDisc(disc1);
        mediaResource.addDisc(disc2);
        ObjectMapper mapper = new ObjectMapper();
        String json = "[" + mapper.writeValueAsString(disc1) + "," + mapper.writeValueAsString(disc2) + "]";
        Response response = mediaResource.getDiscs();
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void getDiscTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addDisc(disc1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(disc1);
        Response response = mediaResource.getDisc(disc1.getBarcode());
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void getDiscNotAvailableTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        String json = MediaServiceResult.BARCODE_NOT_FOUND.getStatus();
        Response response = mediaResource.getDisc(disc1.getBarcode());
        assertEquals(json, response.getEntity());
    }
	
	@Test
	public void updateDiscBarcodeMismatchTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addDisc(disc1);
        Response response = mediaResource.updateDisc(disc1.getBarcode(), disc2);
        assertEquals(MediaServiceResult.BARCODE_MISMATCH.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.BARCODE_MISMATCH.getStatus(), response.getEntity());
    }
	
	@Test
	public void updateDiscTest() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.addDisc(disc1);
        Response response = mediaResource.updateDisc(disc1.getBarcode(), new Disc("Updated Title", disc1.getBarcode(), "New Director", 0));
        assertEquals(MediaServiceResult.OK.getCode(), response.getStatus());
        assertEquals(MediaServiceResult.OK.getStatus(), response.getEntity());
    }
}
