/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.storage;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;

import java.util.List;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 18.06.17
 */
public interface MediaStorage {
    
    /**
     * addBook.
     * @param book Book.
     */
    void addBook(Book book);

    /**
     * containsBook.
     * @param isbn ISBN.
     * @return boolean.
     */
    boolean containsBook(String isbn);

    /**
     * getBooks.
     * @return List of Books.
     */
    List<Book> getBooks();

    /**
     * getBook.
     * @param isbn ISBN.
     * @return Book.
     */
    Book getBook(String isbn);

    /**
     * removeBook.
     * @param isbn ISBN.
     * @return boolean if removed.
     */
    boolean removeBook(String isbn);

    /**
     * addDisc.
     * @param disc Disc.
     */
    void addDisc(Disc disc);

    /**
     * containsDisc.
     * @param barcode Barcode.
     * @return boolean.
     */
    boolean containsDisc(String barcode);

    /**
     * getDiscs.
     * @return List of Discs.
     */
    List<Disc> getDiscs();

    /**
     * getDisc.
     * @param barcode Barcode.
     * @return Disc.
     */
    Disc getDisc(String barcode);

    /**
     * removeDisc.
     * @param barcode Barcode.
     * @return boolean if removed.
     */
    boolean removeDisc(String barcode);
}
