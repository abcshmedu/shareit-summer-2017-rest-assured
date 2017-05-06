package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;

public class DiscTest {

	@Test
	public void emptyDiscToStringTest() {
		Disc disc = new Disc();
		assertEquals("Disc{title='', barcode='', director='', fsk=0}", disc.toString());
	}
	
	@Test
	public void toStringTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals("Disc{title='Title', barcode='12345', director='Director', fsk=6}", disc.toString());
	}

	@Test
	public void gettersTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals("Title", disc.getTitle());
		assertEquals("Director", disc.getDirector());
		assertEquals("12345", disc.getBarcode());
		assertEquals(6, disc.getFsk());
	}
	
	@Test
	public void notEqualsNullTest() {
		Disc disc = new Disc();
		assertEquals(false, disc.equals(null));
	}
	
	@Test
	public void equalsItselfTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals(true, disc.equals(disc));
	}
	
	@Test
	public void notEqualsOtherClassTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals(false, disc.equals(new Book()));
	}
	
	@Test
	public void notEqualsOtherClassTest2() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals(false, disc.equals(new String()));
	}
	
	@Test
	public void notEqualsSuperTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		Disc disc2 = new Disc("OtherTitle", "12345", "Director", 6);
		assertEquals(false, disc.equals(disc2));
	}
	
	@Test
	public void equalsTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		Disc disc2 = new Disc("Title", "12345", "Director", 6);
		assertEquals(true, disc.equals(disc2));
	}
	
	@Test
	public void notEqualDirectorTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		Disc disc2 = new Disc("Title", "12345", "OtherDirector", 6);
		assertEquals(false, disc.equals(disc2));
	}
	
	@Test
	public void notEqualBarcodeTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		Disc disc2 = new Disc("Title", "123456789", "Director", 6);
		assertEquals(false, disc.equals(disc2));
	}
	
	@Test
	public void notEqualFskTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		Disc disc2 = new Disc("Title", "12345", "Director", 12);
		assertEquals(false, disc.equals(disc2));
	}
	
	@Test
	public void sameHashCodeTest() {
		Disc disc = new Disc("Title", "12345", "Director", 6);
		assertEquals(disc.hashCode(), disc.hashCode());
	}
}
