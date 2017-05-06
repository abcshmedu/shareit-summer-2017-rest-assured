package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;

public class BookTest {

	@Test
	public void emptyBookToStringTest() {
		Book book = new Book();
		assertEquals("Book{title='', author='', isbn=''}", book.toString());
	}
	
	@Test
	public void toStringTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals("Book{title='Title', author='Author', isbn='12345'}", book.toString());
	}

	@Test
	public void gettersTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals("Title", book.getTitle());
		assertEquals("Author", book.getAuthor());
		assertEquals("12345", book.getIsbn());
	}
	
	@Test
	public void notEqualsNullTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals(false, book.equals(null));
	}
	
	@Test
	public void equalsItselfTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals(true, book.equals(book));
	}
	
	@Test
	public void notEqualsOtherClassTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals(false, book.equals(new Disc()));
	}
	
	@Test
	public void notEqualsSuperTest() {
		Book book = new Book("Title", "Author", "12345");
		Book book2 = new Book("notEquals", "Author", "12345");
		assertEquals(false, book.equals(book2));
	}
	
	@Test
	public void equalsTest() {
		Book book = new Book("Title", "Author", "12345");
		Book book2 = new Book("Title", "Author", "12345");
		assertEquals(true, book.equals(book2));
	}
	
	@Test
	public void notEqualAuthorsTest() {
		Book book = new Book("Title", "Author", "12345");
		Book book2 = new Book("Title", "otherAuthor", "12345");
		assertEquals(false, book.equals(book2));
	}
	
	@Test
	public void notEqualIsbnTest() {
		Book book = new Book("Title", "Author", "12345");
		Book book2 = new Book("Title", "Author", "otherIsbn");
		assertEquals(false, book.equals(book2));
	}
	
	@Test
	public void sameHashCodeTest() {
		Book book = new Book("Title", "Author", "12345");
		assertEquals(book.hashCode(), book.hashCode());
	}
}
