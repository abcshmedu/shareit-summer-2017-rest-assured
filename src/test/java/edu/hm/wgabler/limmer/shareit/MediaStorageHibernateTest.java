package edu.hm.wgabler.limmer.shareit;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.storage.MediaStorage;
import edu.hm.shareit.storage.MediaStorageHibernate;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * MediaStorageImplTest.
 *
 * @author Andrea Limmer, limmer@hm.edu
 */
public class MediaStorageHibernateTest {

    private final Book stdBook = new Book("Title", "Author", "123");
    private final Disc stdDisc = new Disc("Title", "123", "Director", 6);

    /**
     * Test addBook method.
     */
    @Test
    public void addBookTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(true, mediaStorage.containsBook(stdBook.getIsbn()));
    }

    /**
     * Test containsBook method.
     */
    @Test
    public void containsBookTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(true, mediaStorage.containsBook("123"));
    }

    /**
     * Test containsBook method for non existing book.
     */
    @Test
    public void notContainsBookTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(false, mediaStorage.containsBook("12345"));
    }

    /**
     * Test getBooks method.
     */
    @Test
    public void getBooksTest() {
        Book book = new Book("SecondTitle", "SecondAuthor", "000");
        MediaStorage mediaStorage = addStdBook();
        mediaStorage.addBook(book);
        List<Book> books = new LinkedList<>();
        books.add(stdBook);
        books.add(book);
        assertEquals(books, mediaStorage.getBooks());
    }

    /**
     * Test getBook method.
     */
    @Test
    public void getBookTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(stdBook, mediaStorage.getBook("123"));
    }

    /**
     * Test getBook method for non existing isbn.
     */
    @Test
    public void getBookNotAvailableTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(null, mediaStorage.getBook("12345"));
    }

    /**
     * Test removeBook method.
     */
    @Test
    public void removeBookTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(true, mediaStorage.removeBook("123"));
        assertEquals(false, mediaStorage.containsBook("123"));
    }

    /**
     * Test removeBook method.
     */
    @Test
    public void removeBookTest2() {
        MediaStorage mediaStorage = addStdBook();
        mediaStorage.addBook(new Book("SecondTitle", "SecondAuthor", "000"));
        assertEquals(true, mediaStorage.removeBook("123"));
        assertEquals(false, mediaStorage.containsBook("123"));
        assertEquals(true, mediaStorage.containsBook("000"));
    }

    /**
     * Test removeBook method for non existing isbn.
     */
    @Test
    public void removeBookNotAvailableTest() {
        MediaStorage mediaStorage = addStdBook();
        assertEquals(false, mediaStorage.removeBook("12345"));
        assertEquals(true, mediaStorage.containsBook(stdBook.getIsbn()));
    }

    /**
     * private method for easier testing.
     * Adds the stdBook to a new mediaStorage.
     *
     * @return mediaStorage
     */
    private MediaStorage addStdBook() {
        MediaStorage mediaStorage = new MediaStorageHibernate();
        mediaStorage.addBook(stdBook);
        return mediaStorage;
    }

    // Disc Tests

    /**
     * private method for easier testing.
     * Adds the stdDisc to a new mediaStorage.
     *
     * @return mediaStorage
     */
    private MediaStorage addStdDisc() {
        MediaStorage mediaStorage = new MediaStorageHibernate();
        mediaStorage.addDisc(stdDisc);
        return mediaStorage;
    }

    /**
     * Test addDisc method.
     */
    @Test
    public void addDiscTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(true, mediaStorage.containsDisc(stdDisc.getBarcode()));
    }

    /**
     * Test containsDisc method.
     */
    @Test
    public void containsDiscTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(true, mediaStorage.containsDisc("123"));
    }

    /**
     * Test containsDisc method for non existing barcode.
     */
    @Test
    public void notContainsDiscTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(false, mediaStorage.containsDisc("12345"));
    }

    /**
     * Test getDiscs method.
     */
    @Test
    public void getDiscsTest() {
        Disc disc = new Disc("OtherDisc", "123", "OtherDirector", 0);
        MediaStorage mediaStorage = addStdDisc();
        mediaStorage.addDisc(disc);
        List<Disc> discs = new LinkedList<>();
        discs.add(stdDisc);
        discs.add(disc);
        assertEquals(discs, mediaStorage.getDiscs());
    }

    /**
     * Test getDisc method.
     */
    @Test
    public void getDiscTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(stdDisc, mediaStorage.getDisc("123"));
    }

    /**
     * Test getDisc method fon non existing barcode.
     */
    @Test
    public void getDiscNotAvailableTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(null, mediaStorage.getDisc("12345"));
    }

    /**
     * Test removeDisc method.
     */
    @Test
    public void removeDiscTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(true, mediaStorage.removeDisc("123"));
        assertEquals(false, mediaStorage.containsDisc("123"));
    }

    /**
     * Test removeDisc method.
     */
    @Test
    public void removeDiscTest2() {
        MediaStorage mediaStorage = addStdDisc();
        mediaStorage.addDisc(new Disc("OtherDisc", "12345", "OtherDirector", 0));
        assertEquals(true, mediaStorage.removeDisc("123"));
        assertEquals(false, mediaStorage.containsDisc("123"));
        assertEquals(true, mediaStorage.containsDisc("12345"));
    }

    /**
     * Test removeDisc method for non existing barcode.
     */
    @Test
    public void removeDiscNotAvailableTest() {
        MediaStorage mediaStorage = addStdDisc();
        assertEquals(false, mediaStorage.removeDisc("12345"));
        assertEquals(true, mediaStorage.containsDisc("123"));
    }

    /**
     * Test both addBook & addDisc methods.
     */
    @Test
    public void addBookAndDiscTest() {
        MediaStorage mediaStorage = addStdBook();
        mediaStorage.addDisc(stdDisc);
        assertEquals(true, mediaStorage.containsBook(stdBook.getIsbn()));
        assertEquals(true, mediaStorage.containsDisc(stdDisc.getBarcode()));
    }

    /**
     * Test equals method.
     * Not equals null.
     */
    @Test
    public void notEqualsNullTest() {
        assertEquals(false, new MediaStorageHibernate().equals(null));
    }

    /**
     * Test equals method.
     * Equals itself.
     */
    @Test
    public void equalsItselfTest() {
        MediaStorage storage = new MediaStorageHibernate();
        assertEquals(true, storage.equals(storage));
    }

    /**
     * Test equals method.
     * Not equals object of other class.
     */
    @Test
    public void notEqualsOtherClassTest() {
        MediaStorage storage = new MediaStorageHibernate();
        assertEquals(false, storage.equals(new String()));
    }

    /**
     * Test equals method.
     * Not equals other mediaStorage.
     */
    @Test
    public void notEqualsOtherBooksTest() {
        MediaStorage storage = new MediaStorageHibernate();
        MediaStorage storage2 = new MediaStorageHibernate();
        storage2.addBook(new Book());
        assertEquals(false, storage.equals(storage2));
    }

    /**
     * Test equals method.
     * Not equals other mediaStorage.
     */
    @Test
    public void notEqualsOtherDiscsTest() {
        MediaStorage storage = new MediaStorageHibernate();
        MediaStorage storage2 = new MediaStorageHibernate();
        storage2.addDisc(new Disc());
        assertEquals(false, storage.equals(storage2));
    }

    /**
     * Test equals method.
     * Not equals other mediaStorage.
     */
    @Test
    public void notEqualsOtherBooksAndDiscsTest() {
        MediaStorage storage = new MediaStorageHibernate();
        storage.addBook(new Book());
        MediaStorage storage2 = new MediaStorageHibernate();
        storage2.addDisc(stdDisc);
        storage2.addBook(stdBook);
        assertEquals(false, storage.equals(storage2));
    }
}
