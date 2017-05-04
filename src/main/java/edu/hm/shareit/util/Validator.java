/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.util;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 03.05.17
 */
public abstract class Validator {

	/**
	 * Checks if a given ISBN-13 is valid.
	 * @param isbn ISBN to validate (ISBN-13)
	 * @return true if ISBN is valid
	 * 
	 * source: http://www.moreofless.co.uk/validate-isbn-13-java/
	 */
    public static boolean isValidIsbn(String isbn) {
    	if ( isbn == null )
        {
            return false;
        }

        //remove any hyphens
        isbn = isbn.replaceAll( "-", "" );

        //must be a 13 digit ISBN
        if ( isbn.length() != 13 )
        {
            return false;
        }

        try
        {
            int tot = 0;
            for ( int i = 0; i < 12; i++ )
            {
                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if ( checksum == 10 )
            {
                checksum = 0;
            }

            return checksum == Integer.parseInt( isbn.substring( 12 ) );
        }
        catch ( NumberFormatException nfe )
        {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }

    /**
     * Checks if a given EAN-13 barcode is valid.
     * @param barcode an EAN-13 barcode 
     * @return true if the barcode is valid
     * 
     * source: http://www.logikdev.com/2010/05/13/validate-your-ean-barcode/
     * (but modified some parts)
     */
    public static boolean isValidBarcode(String barcode) {
    	if(barcode == null) {
    		return false;
    	}
    	
    	try {
    	 	// Add five 0 if the code has only 8 digits
	    	if (barcode.length() == 8 ) {
		    	barcode = "00000" + barcode;
	    	}
	    	// Check for 13 digits otherwise
	    	else if (barcode.length() != 13) {
		    	return false;
	    	}
	    	
	    	// Get the check number
	    	int originalCheck = Integer.parseInt(barcode.substring(barcode.length() - 1));
	    	barcode = barcode.substring(0, barcode.length() - 1);
	    	
	    	// Add even numbers together
	    	int even = Integer.parseInt(barcode.substring(1, 2)) +
	    			Integer.parseInt(barcode.substring(3, 4)) +
	    			Integer.parseInt(barcode.substring(5, 6)) +
	    			Integer.parseInt(barcode.substring(7, 8)) +
	    			Integer.parseInt(barcode.substring(9, 10)) +
	    			Integer.parseInt(barcode.substring(11, 12));
	    	// Multiply this result by 3
	    	even *= 3;
	    	
	    	// Add odd numbers together
	    	int odd = Integer.parseInt(barcode.substring(0, 1)) +
	    			Integer.parseInt(barcode.substring(2, 3)) +
	    			Integer.parseInt(barcode.substring(4, 5)) +
	    			Integer.parseInt(barcode.substring(6, 7)) +
	    			Integer.parseInt(barcode.substring(8, 9)) +
	    			Integer.parseInt(barcode.substring(10, 11));
	    	
	    	// Add two totals together
	    	int total = even + odd;
	
	    	// Calculate the checksum
	    	// Divide total by 10 and store the remainder
	    	int checksum = total % 10;
	    	// If result is not 0 then take away 10
	    	if (checksum != 0) {
	    		 checksum = 10 - checksum;
	    	}
	
	    	// Return the result
	    	if (checksum != originalCheck) {
	    		return false;
	    	}
	    	return true;
    	}
        catch ( NumberFormatException nfe )
        {
            //to catch invalid barcodes that have non-numeric characters in them
            return false;
        }
    }
}
