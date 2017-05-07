package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Copy;

/**
 * CopyTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class CopyTest {

    /**
     * Test toString method of copy with empty book.
     */
    @Test
    public void copyWithEmptyBookToStringTest() {
        Copy copy = new Copy(new Book(), "");
        assertEquals("Copy{medium=Book{title='', author='', isbn=''}, owner=''}", copy.toString());
    }
    
    /**
     * Test toString method.
     */
    @Test
    public void toStringTest() {
        Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
        assertEquals("Copy{medium=Book{title='Title', author='Author', isbn='123'}, owner='Me'}", copy.toString());
    }

    /**
     * Test the getters.
     */
    @Test
    public void gettersTest() {
        Book book = new Book("Title", "Author", "123");
        Copy copy = new Copy(book, "Me");
        assertEquals(book, copy.getMedium());
        assertEquals("Me", copy.getOwner());
    }
    
    /**
     * Test equals method.
     * Copy not equals null.
     */
    @Test
    public void notEqualsNullTest() {
        Copy copy = new Copy(new Book(), "");
        assertEquals(false, copy.equals(null));
    }
    
    /**
     * Test equals method.
     * Copy equals itself.
     */
    @Test
    public void equalsItselfTest() {
        Copy copy = new Copy(new Book(), "");
        assertEquals(true, copy.equals(copy));
    }
    
    /**
     * Test equals method.
     * Copy not equals object of other class.
     */
    @Test
    public void notEqualsOtherClassTest() {
        Copy copy = new Copy(new Book(), "");
        assertEquals(false, copy.equals(new Book()));
    }
    
    /**
     * Test equals method.
     */
    @Test
    public void equalsTest() {
        Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
        Copy copy2 = new Copy(new Book("Title", "Author", "123"), "Me");
        assertEquals(true, copy.equals(copy2));
    }
    
    /**
     * Test equals method.
     * Copy not equals copy with other owner.
     */
    @Test
    public void notEqualOwnerTest() {
        Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
        Copy copy2 = new Copy(new Book("Title", "Author", "123"), "You");
        assertEquals(false, copy.equals(copy2));
    }
    
    /**
     * Test equals method.
     * Copy not equals copy with other medium.
     */
    @Test
    public void notEqualMediumTest() {
        Copy copy = new Copy(new Book("Title", "Author", "123"), "Me");
        Copy copy2 = new Copy(new Book("OtherBook", "Author", "123"), "Me");
        assertEquals(false, copy.equals(copy2));
    }
        
    /**
     * Test hashCode method.
     * Equal copys have same hashCode.
     */
    @Test
    public void sameHashCodeTest() {
        Copy copy = new Copy(new Book(), "Me");
        Copy copy2 = new Copy(new Book(), "Me");
        assertEquals(copy.hashCode(), copy2.hashCode());
    }
}
