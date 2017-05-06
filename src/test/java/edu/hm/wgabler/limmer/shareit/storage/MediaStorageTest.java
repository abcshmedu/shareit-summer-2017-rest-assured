package edu.hm.wgabler.limmer.shareit.storage;

import static org.junit.Assert.*;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.storage.MediaStorage;

import org.junit.Test;

public class MediaStorageTest {

	@Test
	public void toStringTest() {
		MediaStorage mediaStorage = new MediaStorage();
		assertEquals("MediaStorage [books=[], discs=[]]", mediaStorage.toString());
	}

	@Test
	public void addBook() {
		MediaStorage mediaStorage = new MediaStorage();
		Book book = new Book("Title", "Author", "123");
		mediaStorage.addBook(book);
		assertEquals("MediaStorage [books=[Book{title='Title', author='Author', isbn='123'}], discs=[]]", 
				mediaStorage.toString());
	}
}
