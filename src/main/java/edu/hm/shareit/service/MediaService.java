/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.service;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

/**
 * Interface for communicating with logical layer.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public interface MediaService {

    /**
     * Add a Book to storage.
     *
     * @param book The new Book.
     * @return Result describing success or failure.
     */
    MediaServiceResult addBook(Book book);

    /**
     * Add a Disc to storage.
     *
     * @param disc The new Disc.
     * @return Result describing success or failure.
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Get an Array of all Books.
     *
     * @return Array of all Books.
     */
    Medium[] getBooks();

    /**
     * Get an Array of all Discs.
     *
     * @return Array of all Discs.
     */
    Medium[] getDiscs();

    /**
     * Return Book for isbn if available.
     *
     * @param isbn Given ISBN.
     * @return Book if available, null otherwise.
     */
    Medium getBook(String isbn);

    /**
     * Return Disc for barcode if available.
     *
     * @param barcode Given barcode.
     * @return Book if available, null otherwise.
     */
    Medium getDisc(String barcode);
    
    /**
     * Update a book.
     * @param book Book.
     * @return MediaServiceResult if updating was possible.
     */
    MediaServiceResult updateBook(Book book);

    /**
     * Update a disc.
     * @param disc disc.
     * @return MediaServiceResult if updating was possible.
     */
    MediaServiceResult updateDisc(Disc disc);
}
