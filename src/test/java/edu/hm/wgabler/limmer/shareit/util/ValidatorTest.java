package edu.hm.wgabler.limmer.shareit.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.util.Validator;

public class ValidatorTest {

	@Test
	public void isbnNullTest() {
		actAndAssertIsbn(null, false);
	}

	@Test
	public void isbnTooShortTest() {
		actAndAssertIsbn("123456789", false);
	}
	
	@Test
	public void isbnTooLongTest() {
		actAndAssertIsbn("12345678901234", false);
	}
	
	@Test
	public void isbnRandomIsbnTest() {
		actAndAssertIsbn("1234567890123", false);
	}
	
	@Test
	public void anotherRandomIsbnTest() {
		actAndAssertIsbn("934-1293-498-276", false);
	}
	
	@Test
	public void validIsbnTest() {
		actAndAssertIsbn("978-3426521083", true);
	}
	
	@Test
	public void anotherValidIsbnTest() {
		actAndAssertIsbn("978-3453435797", true);
	}
	
	@Test
	public void isbnMod10Test() {
		actAndAssertIsbn("2121212121210", true);
	}
	
	@Test
	public void isbnWithLettersTest() {
		actAndAssertIsbn("978-34ab521083", false);
	}
	
	private void actAndAssertIsbn(String isbn, boolean expectedResult) {
		assertEquals(expectedResult, Validator.isValidIsbn(isbn));
	}
	
	private void actAndAssertBarcode(String barcode, boolean expectedResult) {
		assertEquals(expectedResult, Validator.isValidBarcode(barcode));
	}
	
	@Test 
	public void barcodeWithLettersTest() {
		actAndAssertBarcode("12345bc8", false);
	}
	
	@Test 
	public void barcode8Test() {
		actAndAssertBarcode("12345678", false);
	}
	
	@Test 
	public void barcodeTooShortTest() {
		actAndAssertBarcode("01234", false);
	}
	
	@Test 
	public void barcodeTooLongTest() {
		actAndAssertBarcode("12345678901234", false);
	}
	
	@Test 
	public void validBarcodeTest() {
		actAndAssertBarcode("7501031311309", true);
	}
	
	@Test 
	public void anotherValidBarcodeTest() {
		actAndAssertBarcode("7501054530107", true);
	}
	
	@Test 
	public void randomBarcodeTest() {
		actAndAssertBarcode("1234567890123", false);
	}
	
	@Test 
	public void barcodeNullTest() {
		actAndAssertBarcode(null, false);
	}
}
