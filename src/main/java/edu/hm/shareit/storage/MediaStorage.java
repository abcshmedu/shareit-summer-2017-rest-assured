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
    void addBook(Book book);

    boolean containsBook(String isbn);

    List<Book> getBooks();

    Book getBook(String isbn);

    boolean removeBook(String isbn);

    void addDisc(Disc disc);

    boolean containsDisc(String barcode);

    List<Disc> getDiscs();

    Disc getDisc(String barcode);

    boolean removeDisc(String barcode);
}
