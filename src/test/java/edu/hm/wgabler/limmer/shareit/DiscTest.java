package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;

/**
 * DiscTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class DiscTest {

    /**
     * Test toString method of empty disc.
     */
    @Test
    public void emptyDiscToStringTest() {
        final Disc disc = new Disc();
        assertEquals("Disc{title='', barcode='', director='', fsk=0}", disc.toString());
    }
    
    /**
     * Test toString method.
     */
    @Test
    public void toStringTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        assertEquals("Disc{title='Title', barcode='12345', director='Director', fsk=6}", disc.toString());
    }

    /**
     * Test the getters.
     */
    @Test
    public void gettersTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        assertEquals("Title", disc.getTitle());
        assertEquals("Director", disc.getDirector());
        assertEquals("12345", disc.getBarcode());
        final int fsk = 6;
        assertEquals(fsk, disc.getFsk());
    }
    
    /**
     * Test equals method.
     * Disc not equals null.
     */
    @Test
    public void notEqualsNullTest() {
        final Disc disc = new Disc();
        assertEquals(false, disc.equals(null));
    }
    
    /**
     * Test equals method.
     * Disc equals itself.
     */
    @Test
    public void equalsItselfTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        assertEquals(true, disc.equals(disc));
    }
    
    /**
     * Test equals method.
     * Disc not equals object of other class.
     */
    @Test
    public void notEqualsOtherClassTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        assertEquals(false, disc.equals(new Book()));
    }
    
    /**
     * Test equals method.
     * Disc not equals null.
     */
    @Test
    public void notEqualsOtherClassTest2() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        assertEquals(false, disc.equals(new String()));
    }
    
    /**
     * Test equals method.
     * Disc not equals disc with other title.
     */
    @Test
    public void notEqualsSuperTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("OtherTitle", "12345", "Director", 6);
        assertEquals(false, disc.equals(disc2));
    }
    
    /**
     * Test equals method.
     */
    @Test
    public void equalsTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("Title", "12345", "Director", 6);
        assertEquals(true, disc.equals(disc2));
    }
    
    /**
     * Test equals method.
     * Disc not equals disc with other director.
     */
    @Test
    public void notEqualDirectorTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("Title", "12345", "OtherDirector", 6);
        assertEquals(false, disc.equals(disc2));
    }
    
    /**
     * Test equals method.
     * Disc not equals disc with other barcode.
     */
    @Test
    public void notEqualBarcodeTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("Title", "123456789", "Director", 6);
        assertEquals(false, disc.equals(disc2));
    }
    
    /**
     * Test equals method.
     * Disc not equals disc with other fsk.
     */
    @Test
    public void notEqualFskTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("Title", "12345", "Director", 12);
        assertEquals(false, disc.equals(disc2));
    }
    
    /**
     * Test hashCode method.
     * Equal discs have same hashCode.
     */
    @Test
    public void sameHashCodeTest() {
        final Disc disc = new Disc("Title", "12345", "Director", 6);
        final Disc disc2 = new Disc("Title", "12345", "Director", 6);
        assertEquals(disc.hashCode(), disc2.hashCode());
    }
}
