package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.storage.MediaStorage;

import org.junit.Test;

public class MediaStorageTest {

	private final Book stdBook = new Book("Title", "Author", "123");
	private final Disc stdDisc = new Disc("Title", "123", "Director", 6);
	
	@Test
	public void toStringTest() {
		MediaStorage mediaStorage = new MediaStorage();
		assertEquals("MediaStorage [books=[], discs=[]]", mediaStorage.toString());
	}

	@Test
	public void addBookTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals("MediaStorage [books=[Book{title='Title', author='Author', isbn='123'}], discs=[]]", 
				mediaStorage.toString());
	}
	
	@Test
	public void containsBookTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(true, mediaStorage.containsBook("123"));
	}
	
	@Test
	public void notContainsBookTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(false, mediaStorage.containsBook("12345"));
	}
	
	@Test
	public void getBooksTest() {
		Book book = new Book("SecondTitle", "SecondAuthor", "000");
		MediaStorage mediaStorage = addStdBook();
		mediaStorage.addBook(book);
		List<Book> books = new LinkedList<>();
		books.add(stdBook);
		books.add(book);		
		assertEquals(books, mediaStorage.getBooks());
	}
	
	@Test
	public void getBookTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(stdBook, mediaStorage.getBook("123"));
	}
	
	@Test
	public void getBookNotAvailableTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(null, mediaStorage.getBook("12345"));
	}
		
	@Test
	public void removeBookTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(true, mediaStorage.removeBook("123"));
		assertEquals("MediaStorage [books=[], discs=[]]", mediaStorage.toString());
	}
	
	@Test
	public void removeBookTest2() {
		MediaStorage mediaStorage = addStdBook();
		mediaStorage.addBook(new Book("SecondTitle", "SecondAuthor", "000"));
		assertEquals(true, mediaStorage.removeBook("123"));
		assertEquals("MediaStorage [books=[Book{title='SecondTitle', author='SecondAuthor', isbn='000'}], discs=[]]", mediaStorage.toString());
	}
	
	@Test
	public void removeBookNotAvailableTest() {
		MediaStorage mediaStorage = addStdBook();
		assertEquals(false, mediaStorage.removeBook("12345"));
		assertEquals("MediaStorage [books=[Book{title='Title', author='Author', isbn='123'}], discs=[]]", mediaStorage.toString());
	}
	
	private MediaStorage addStdBook() {
		MediaStorage mediaStorage = new MediaStorage();
		mediaStorage.addBook(stdBook);
		return mediaStorage;
	}
	
	// Disc Tests
	
	private MediaStorage addStdDisc() {
		MediaStorage mediaStorage = new MediaStorage();
		mediaStorage.addDisc(stdDisc);
		return mediaStorage;
	}
	
	@Test
	public void addDiscTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals("MediaStorage [books=[], discs=[Disc{title='Title', barcode='123', director='Director', fsk=6}]]", 
				mediaStorage.toString());
	}
	
	@Test
	public void containsDiscTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(true, mediaStorage.containsDisc("123"));
	}
	
	@Test
	public void notContainsDiscTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(false, mediaStorage.containsDisc("12345"));
	}
	
	@Test
	public void getDiscsTest() {
		Disc disc = new Disc("OtherDisc", "123", "OtherDirector", 6);
		MediaStorage mediaStorage = addStdDisc();
		mediaStorage.addDisc(disc);
		List<Disc> discs = new LinkedList<>();
		discs.add(stdDisc);
		discs.add(disc);		
		assertEquals(discs, mediaStorage.getDiscs());
	}
	
	@Test
	public void getDiscTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(stdDisc, mediaStorage.getDisc("123"));
	}
	
	@Test
	public void getDiscNotAvailableTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(null, mediaStorage.getDisc("12345"));
	}
	
	@Test
	public void removeDiscTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(true, mediaStorage.removeDisc("123"));
		assertEquals("MediaStorage [books=[], discs=[]]", mediaStorage.toString());
	}
	
	@Test
	public void removeDiscTest2() {
		MediaStorage mediaStorage = addStdDisc();
		mediaStorage.addDisc(new Disc("OtherDisc", "12345", "OtherDirector", 6));
		assertEquals(true, mediaStorage.removeDisc("123"));
		assertEquals("MediaStorage [books=[], discs=[Disc{title='OtherDisc', barcode='12345', director='OtherDirector', fsk=6}]]", mediaStorage.toString());
	}
	
	@Test
	public void removeDiscNotAvailableTest() {
		MediaStorage mediaStorage = addStdDisc();
		assertEquals(false, mediaStorage.removeDisc("12345"));
		assertEquals("MediaStorage [books=[], discs=[Disc{title='Title', barcode='123', director='Director', fsk=6}]]", mediaStorage.toString());
	}
	
	
	@Test
	public void addBookAndDiscTest() {
		MediaStorage mediaStorage = addStdBook();
		mediaStorage.addDisc(stdDisc);
		assertEquals("MediaStorage [books=[Book{title='Title', author='Author', isbn='123'}], discs=[Disc{title='Title', barcode='123', director='Director', fsk=6}]]", mediaStorage.toString());
	}
}
