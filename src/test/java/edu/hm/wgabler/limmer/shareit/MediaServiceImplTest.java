package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceImpl;
import edu.hm.shareit.service.MediaServiceResult;
import edu.hm.shareit.storage.MediaStorage;

public class MediaServiceImplTest {

	private final Book stdBook = new Book("Book Title", "Author", "978-3426521083");
	private final Disc stdDisc = new Disc("Disc Title", "7501031311309", "Director", 0);
	private final Book otherBook = new Book("Other Book", "Other Author", "978-3453435797");
	private final Disc otherDisc = new Disc("Other Disc", "7501054530107", "Other Director", 6);
	
	@Test
	public void addBookTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.OK, service.addBook(stdBook));
		MediaStorage exp = new MediaStorage();
		exp.addBook(stdBook);
		assertEquals(exp, service.getMediaStorage());
	}
	
	@Test
	public void addEmptyBookTest() {
		MediaService service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.MISSING_TITLE, service.addBook(new Book()));		
	}

	@Test
	public void addBookWithoutAuthorTest() {
		MediaService service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.MISSING_AUTHOR, service.addBook(new Book("Title", "", "xyz")));		
	}
	
	@Test
	public void addBookInvalidIsbnTest() {
		MediaService service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.INVALID_ISBN, service.addBook(new Book("Title", "Author", "123")));		
	}
	
	@Test
	public void addBookAlreadyInUseTest() {
		MediaService service = new MediaServiceImpl();
		service.addBook(stdBook);
		assertEquals(MediaServiceResult.ISBN_ALREADY_IN_USE, service.addBook(new Book("Title", "Author", "978-3426521083")));		
	}
	
	@Test
	public void getBooksTest() {
		MediaService service = new MediaServiceImpl();
		service.addBook(stdBook);
		service.addBook(otherBook);
		Medium[] array = new Book[] {stdBook, otherBook};
		assertArrayEquals(array, service.getBooks());
	}
	
	@Test
	public void getBooksEmptyTest() {
		MediaService service = new MediaServiceImpl();
		Medium[] array = new Book[0];
		assertArrayEquals(array, service.getBooks());
	}
	
	@Test
	public void getBookInvalidIsbnTest() {
		MediaService service = new MediaServiceImpl();
		service.addBook(stdBook);
		assertEquals(null, service.getBook("123"));		
	}
	
	@Test
	public void getBookNotAvailableTest() {
		MediaService service = new MediaServiceImpl();
		service.addBook(stdBook);
		assertEquals(null, service.getBook("978-3453435797"));		
	}
	
	@Test
	public void getBookTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		service.addBook(stdBook);
		assertEquals(stdBook, service.getBook("978-3426521083"));		
	}
	
	@Test
	public void getBookTest2() {
		MediaServiceImpl service = new MediaServiceImpl();
		service.addBook(otherBook);
		assertEquals(otherBook, service.getBook(otherBook.getIsbn()));		
	}
	
	
	@Test
	public void updateBookEmptyBookTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.MISSING_TITLE, service.updateBook(new Book()));		
	}
	
	@Test
	public void updateBookMissingAuthorTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.MISSING_AUTHOR, service.updateBook(new Book("Title", "", "123")));		
	}
	
	@Test
	public void updateBookInvalidIsbnTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.INVALID_ISBN, service.updateBook(new Book("Title", "Author", "123")));		
	}
	
	@Test
	public void updateBookIsbnNotFoundTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		assertEquals(MediaServiceResult.ISBN_NOT_FOUND, service.updateBook(new Book("Title", "Author", "978-3426521083")));		
	}
	
	@Test
	public void updateBookTest() {
		MediaServiceImpl service = new MediaServiceImpl();
		service.addBook(stdBook);		
		assertEquals(MediaServiceResult.OK, service.updateBook(new Book("New", "New Author", stdBook.getIsbn())));
	}
	
}
