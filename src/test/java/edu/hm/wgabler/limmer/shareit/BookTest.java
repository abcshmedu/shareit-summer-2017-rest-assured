package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;

/**
 * BookTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class BookTest {

    /**
     * Test toString method for empty book.
     */
    @Test
    public void emptyBookToStringTest() {
        Book book = new Book();
        assertEquals("Book{title='', author='', isbn=''}", book.toString());
    }
    
    /**
     * Test toString method.
     */
    @Test
    public void toStringTest() {
        Book book = new Book("Title", "Author", "12345");
        assertEquals("Book{title='Title', author='Author', isbn='12345'}", book.toString());
    }

    /**
     * Test the getters.
     */
    @Test
    public void gettersTest() {
        Book book = new Book("Title", "Author", "12345");
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("12345", book.getIsbn());
    }
    
    /**
     * Test equals method.
     * Book not equal null.
     */
    @Test
    public void notEqualsNullTest() {
        Book book = new Book("Title", "Author", "12345");
        assertEquals(false, book.equals(null));
    }
    
    /**
     * Test equals method.
     * Book equals itself.
     */
    @Test
    public void equalsItselfTest() {
        Book book = new Book("Title", "Author", "12345");
        assertEquals(true, book.equals(book));
    }
    
    /**
     * Test equals method.
     * Book not equals object of other class.
     */
    @Test
    public void notEqualsOtherClassTest() {
        Book book = new Book("Title", "Author", "12345");
        assertEquals(false, book.equals(new Disc()));
    }
    
    /**
     * Test equals method.
     * Book not equals book with different title.
     */
    @Test
    public void notEqualsSuperTest() {
        Book book = new Book("Title", "Author", "12345");
        Book book2 = new Book("notEquals", "Author", "12345");
        assertEquals(false, book.equals(book2));
    }
    
    /**
     * Test equals method.
     */
    @Test
    public void equalsTest() {
        Book book = new Book("Title", "Author", "12345");
        Book book2 = new Book("Title", "Author", "12345");
        assertEquals(true, book.equals(book2));
    }
    
    /**
     * Test equals method.
     * Book not equals book with different author.
     */
    @Test
    public void notEqualAuthorsTest() {
        Book book = new Book("Title", "Author", "12345");
        Book book2 = new Book("Title", "otherAuthor", "12345");
        assertEquals(false, book.equals(book2));
    }
    
    /**
     * Test equals method.
     * Book not equals book with different isbn.
     */
    @Test
    public void notEqualIsbnTest() {
        Book book = new Book("Title", "Author", "12345");
        Book book2 = new Book("Title", "Author", "otherIsbn");
        assertEquals(false, book.equals(book2));
    }
    
    /**
     * Test hashCode method.
     * Equal books have the same hashCode.
     */
    @Test
    public void sameHashCodeTest() {
        Book book = new Book("Title", "Author", "12345");
        Book book2 = new Book("Title", "Author", "12345");
        assertEquals(book.hashCode(), book2.hashCode());
    }
}
