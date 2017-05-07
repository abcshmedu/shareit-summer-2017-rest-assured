package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.util.Validator;

/**
 * ValidatorTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class ValidatorTest {

    /**
     * Null is not valid.
     */
    @Test
    public void isbnNullTest() {
        actAndAssertIsbn(null, false);
    }

    /**
     * Isbn too short.
     */
    @Test
    public void isbnTooShortTest() {
        actAndAssertIsbn("123456789", false);
    }
    
    /**
     * Isbn too long.
     */
    @Test
    public void isbnTooLongTest() {
        actAndAssertIsbn("12345678901234", false);
    }
    
    /**
     * Correct length but invalid.
     */
    @Test
    public void isbnRandomIsbnTest() {
        actAndAssertIsbn("1234567890123", false);
    }
    
    /**
     * Correct length but invalid.
     */
    @Test
    public void anotherRandomIsbnTest() {
        actAndAssertIsbn("934-1293-498-276", false);
    }
    
    /**
     * Valid isbn.
     */
    @Test
    public void validIsbnTest() {
        actAndAssertIsbn("978-3426521083", true);
    }
    
    /**
     * Valid isbn.
     */
    @Test
    public void anotherValidIsbnTest() {
        actAndAssertIsbn("978-3453435797", true);
    }
    
    /**
     * Valid isbn to test specific part of the validator.
     */
    @Test
    public void isbnMod10Test() {
        actAndAssertIsbn("2121212121210", true);
    }
    
    /**
     * Isbn contains letters.
     */
    @Test
    public void isbnWithLettersTest() {
        actAndAssertIsbn("978-34ab521083", false);
    }
    
    /**
     * Private method to check if the given isbn is valid.
     * @param isbn Isbn.
     * @param expectedResult if isbn is expected to be valid.
     */
    private void actAndAssertIsbn(String isbn, boolean expectedResult) {
        assertEquals(expectedResult, Validator.isValidIsbn(isbn));
    }
    
    /**
     * Private method to check if the given barcode is valid.
     * @param barcode Barcode.
     * @param expectedResult if barcode is expected to be valid.
     */
    private void actAndAssertBarcode(String barcode, boolean expectedResult) {
        assertEquals(expectedResult, Validator.isValidBarcode(barcode));
    }
    
    /**
     * Barcode contains letters.
     */
    @Test 
    public void barcodeWithLettersTest() {
        actAndAssertBarcode("12345bc8", false);
    }
    
    /**
     * Barcode with 8 chars.
     */
    @Test 
    public void barcode8Test() {
        actAndAssertBarcode("12345678", false);
    }
    
    /**
     * Barcode too short.
     */
    @Test 
    public void barcodeTooShortTest() {
        actAndAssertBarcode("01234", false);
    }
    
    /**
     * Barcode too long.
     */
    @Test 
    public void barcodeTooLongTest() {
        actAndAssertBarcode("12345678901234", false);
    }
    
    /**
     * Valid barcode.
     */
    @Test 
    public void validBarcodeTest() {
        actAndAssertBarcode("7501031311309", true);
    }
    
    /**
     * Valid barcode.
     */
    @Test 
    public void anotherValidBarcodeTest() {
        actAndAssertBarcode("7501054530107", true);
    }
    
    /**
     * Correct length but invalid.
     */
    @Test 
    public void randomBarcodeTest() {
        actAndAssertBarcode("1234567890123", false);
    }
    
    /**
     * Null is not a valid barcode.
     */
    @Test 
    public void barcodeNullTest() {
        actAndAssertBarcode(null, false);
    }
}
