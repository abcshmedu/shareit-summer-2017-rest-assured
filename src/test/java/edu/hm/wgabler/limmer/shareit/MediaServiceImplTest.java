package edu.hm.wgabler.limmer.shareit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceImpl;
import edu.hm.shareit.service.MediaServiceResult;

/**
 * MediaServiceImplTest.
 * @author Andrea Limmer, limmer@hm.edu
 */
public class MediaServiceImplTest {

    private final Book stdBook = new Book("Book Title", "Author", "978-3426521083");
    private final Disc stdDisc = new Disc("Disc Title", "7501031311309", "Director", 0);
    private final Book otherBook = new Book("Other Book", "Other Author", "978-3453435797");
    private final Disc otherDisc = new Disc("Other Disc", "7501054530107", "Other Director", 6);
    
    /**
     * Test addBook method.
     */
    @Test
    public void addBookTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.OK, service.addBook(stdBook));
    }
    
    /**
     * Test addBook method for empty book.
     */
    @Test
    public void addEmptyBookTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_TITLE, service.addBook(new Book()));        
    }

    /**
     * Test addBook method for book without an author.
     */
    @Test
    public void addBookWithoutAuthorTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_AUTHOR, service.addBook(new Book("Title", "", "xyz")));        
    }
    
    /**
     * Test addBook method for book with invalid isbn.
     */
    @Test
    public void addBookInvalidIsbnTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.INVALID_ISBN, service.addBook(new Book("Title", "Author", "123")));        
    }
    
    /**
     * Test addBook method for book with isbn which is already in use.
     */
    @Test
    public void addBookAlreadyInUseTest() {
        MediaService service = new MediaServiceImpl();
        service.addBook(stdBook);
        assertEquals(MediaServiceResult.ISBN_ALREADY_IN_USE, service.addBook(new Book("Title", "Author", "978-3426521083")));        
    }
    
    /**
     * Test getBooks method.
     */
    @Test
    public void getBooksTest() {
        MediaService service = new MediaServiceImpl();
        service.addBook(stdBook);
        service.addBook(otherBook);
        Medium[] array = new Book[] {stdBook, otherBook};
        assertArrayEquals(array, service.getBooks());
    }
    
    /**
     * Test getBooks method for empty mediaService.
     */
    @Test
    public void getBooksEmptyTest() {
        MediaService service = new MediaServiceImpl();
        Medium[] array = new Book[0];
        assertArrayEquals(array, service.getBooks());
    }
    
    /**
     * Test getBook method for book with invalid isbn.
     */
    @Test
    public void getBookInvalidIsbnTest() {
        MediaService service = new MediaServiceImpl();
        service.addBook(stdBook);
        assertEquals(null, service.getBook("123"));        
    }
    
    /**
     * Test getBook method for non existing isbn.
     */
    @Test
    public void getBookNotAvailableTest() {
        MediaService service = new MediaServiceImpl();
        service.addBook(stdBook);
        assertEquals(null, service.getBook("978-3453435797"));        
    }
    
    /**
     * Test getBook method.
     */
    @Test
    public void getBookTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addBook(stdBook);
        assertEquals(stdBook, service.getBook("978-3426521083"));        
    }
    
    /**
     * Test getBook method.
     */
    @Test
    public void getBookTest2() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addBook(otherBook);
        assertEquals(otherBook, service.getBook(otherBook.getIsbn()));        
    }
    
    /**
     * Test updateBook method for empty book.
     */
    @Test
    public void updateBookEmptyTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_TITLE, service.updateBook(new Book()));        
    }
    
    /**
     * Test updateBook method for book with missing author.
     */
    @Test
    public void updateBookMissingAuthorTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_AUTHOR, service.updateBook(new Book("Title", "", "123")));        
    }
    
    /**
     * Test updateBook method for book with invalid isbn.
     */
    @Test
    public void updateBookInvalidIsbnTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.INVALID_ISBN, service.updateBook(new Book("Title", "Author", "123")));        
    }
    
    /**
     * Test updateBook method for non existing isbn.
     */
    @Test
    public void updateBookIsbnNotFoundTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.ISBN_NOT_FOUND, service.updateBook(new Book("Title", "Author", "978-3426521083")));        
    }
    
    /**
     * Test updateBook method.
     */
    @Test
    public void updateBookTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addBook(stdBook);        
        assertEquals(MediaServiceResult.OK, service.updateBook(new Book("New", "New Author", stdBook.getIsbn())));
    }
    
    
        
    // Discs
    
    /**
     * Test addDisc method.
     */
    @Test
    public void addDiscTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.OK, service.addDisc(stdDisc));
    }
    
    /**
     * Test addDisc method for empty disc.
     */
    @Test
    public void addEmptyDiscTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_TITLE, service.addDisc(new Disc()));        
    }

    /**
     * Test addDisc method for disc without director.
     */
    @Test
    public void addDiscWithoutDirectorTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_DIRECTOR, service.addDisc(new Disc("Title", "123", "", 0)));        
    }
    
    /**
     * Test addDisc method for disc with invalid barcode.
     */
    @Test
    public void addDiscInvalidBarcodeTest() {
        MediaService service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.INVALID_BARCODE, service.addDisc(new Disc("Title", "123", "Director", 0)));        
    }
    
    /**
     * Test addDisc method for disc with already used barcode.
     */
    @Test
    public void addDiscAlreadyInUseTest() {
        MediaService service = new MediaServiceImpl();
        service.addDisc(stdDisc);
        assertEquals(MediaServiceResult.BARCODE_ALREADY_IN_USE, service.addDisc(stdDisc));        
    }

    /**
     * Test getDiscs method.
     */
    @Test
    public void getDiscsTest() {
        MediaService service = new MediaServiceImpl();
        service.addDisc(stdDisc);
        service.addDisc(otherDisc);
        Medium[] array = new Disc[] {stdDisc, otherDisc};
        assertArrayEquals(array, service.getDiscs());
    }
    
    /**
     * Test getDiscs method for empty mediaService.
     */
    @Test
    public void getDiscsEmptyTest() {
        MediaService service = new MediaServiceImpl();
        Medium[] array = new Disc[0];
        assertArrayEquals(array, service.getDiscs());
    }
    
    /**
     * Test getDisc method for disc with invalid barcode.
     */
    @Test
    public void getDiscInvalidBarcodeTest() {
        MediaService service = new MediaServiceImpl();
        service.addDisc(stdDisc);
        assertEquals(null, service.getDisc("123"));        
    }
    
    /**
     * Test getDisc method for disc with non existing barcode.
     */
    @Test
    public void getDiscNotAvailableTest() {
        MediaService service = new MediaServiceImpl();
        service.addDisc(stdDisc);
        assertEquals(null, service.getDisc(otherDisc.getBarcode()));        
    }
    
    /**
     * Test getDisc method.
     */
    @Test
    public void getDiscTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addDisc(stdDisc);
        assertEquals(stdDisc, service.getDisc(stdDisc.getBarcode()));        
    }
    
    /**
     * Test getDisc method.
     */
    @Test
    public void getDiscTest2() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addDisc(otherDisc);
        assertEquals(otherDisc, service.getDisc(otherDisc.getBarcode()));        
    }
    
    /**
     * Test updateDisc method for empty disc.
     */
    @Test
    public void updateDiscEmptyTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_TITLE, service.updateDisc(new Disc()));        
    }
    
    /**
     * Test updateDisc for disc with missing director.
     */
    @Test
    public void updateDiscMissingDirectorTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.MISSING_DIRECTOR, service.updateDisc(new Disc("Title", "1234", "", 0)));        
    }
    
    /**
     * Test updateDisc method for disc with invalid barcode.
     */
    @Test
    public void updateDiscInvalidBarcodeTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.INVALID_BARCODE, service.updateDisc(new Disc("Title", "1234", "Director", 0)));        
    }
    
    /**
     * Test updateDisc method for disc with non existing barcode.
     */
    @Test
    public void updateDiscBarcodeNotFoundTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        assertEquals(MediaServiceResult.BARCODE_NOT_FOUND, service.updateDisc(new Disc("Title", stdDisc.getBarcode(), "Director", 0)));        
    }
    
    /**
     * Test updateDisc method.
     */
    @Test
    public void updateDiscTest() {
        MediaServiceImpl service = new MediaServiceImpl();
        service.addDisc(stdDisc);        
        assertEquals(MediaServiceResult.OK, service.updateDisc(new Disc("New", stdDisc.getBarcode(), "New Director", 0)));
    }
}
