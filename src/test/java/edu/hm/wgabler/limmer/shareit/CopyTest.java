package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Copy;
import edu.hm.shareit.media.Disc;

public class CopyTest {

	@Test
	public void emptyCopyWithEmptyBookToStringTest() {
		Copy copy = new Copy(new Book(), "");
		assertEquals("Copy{medium=Book{title='', author='', isbn=''}, owner=''}", copy.toString());
	}
	
	@Test
	public void toStringTest() {
		Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
		assertEquals("Copy{medium=Book{title='Title', author='Author', isbn='123'}, owner='Me'}", copy.toString());
	}

	@Test
	public void gettersTest() {
		Book book = new Book("Title", "Author", "123");
		Copy copy = new Copy(book, "Me");
		assertEquals(book, copy.getMedium());
		assertEquals("Me", copy.getOwner());
	}
	
	@Test
	public void notEqualsNullTest() {
		Copy copy = new Copy(new Book(), "");
		assertEquals(false, copy.equals(null));
	}
	
	@Test
	public void equalsItselfTest() {
		Copy copy = new Copy(new Book(), "");
		assertEquals(true, copy.equals(copy));
	}
	
	@Test
	public void notEqualsOtherClassTest() {
		Copy copy = new Copy(new Book(), "");
		assertEquals(false, copy.equals(new Book()));
	}
	
	@Test
	public void equalsTest() {
		Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
		Copy copy2 = new Copy(new Book("Title", "Author", "123"), "Me");
		assertEquals(true, copy.equals(copy2));
	}
	
	@Test
	public void notEqualOwnerTest() {
		Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
		Copy copy2 = new Copy(new Book("Title", "Author", "123"), "You");
		assertEquals(false, copy.equals(copy2));
	}
	
	@Test
	public void notEqualMediumTest() {
		Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
		Copy copy2 = new Copy(new Book("OtherBook", "Author", "123"), "Me");
		assertEquals(false, copy.equals(copy2));
	}
		
	@Test
	public void sameHashCodeTest() {
		Copy copy = new Copy(new Book(), "Me");
		assertEquals(copy.hashCode(), copy.hashCode());
	}
}
