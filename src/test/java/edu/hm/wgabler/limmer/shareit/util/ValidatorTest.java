package edu.hm.wgabler.limmer.shareit.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.util.Validator;

public class ValidatorTest {

	@Test
	public void isbnNullTest() {
		actAndAssert(null, false);
	}

	@Test
	public void isbnTooShortTest() {
		actAndAssert("123456789", false);
	}
	
	@Test
	public void isbnTooLongTest() {
		actAndAssert("12345678901234", false);
	}
	
	@Test
	public void isbnRandomIsbnTest() {
		actAndAssert("1234567890123", false);
	}
	
	@Test
	public void anotherRandomIsbnTest() {
		actAndAssert("934-1293-498-276", false);
	}
	
	@Test
	public void validIsbnTest() {
		actAndAssert("978-3426521083", true);
	}
	
	@Test
	public void anotherValidIsbnTest() {
		actAndAssert("978-3453435797", true);
	}
	
	@Test
	public void isbnMod10Test() {
		actAndAssert("2121212121210", true);
	}
	
	@Test
	public void isbnWithLettersTest() {
		actAndAssert("978-34ab521083", false);
	}
	
	private void actAndAssert(String isbn, boolean expectedResult) {
		assertEquals(expectedResult, Validator.isValidIsbn(isbn));
	}
}
