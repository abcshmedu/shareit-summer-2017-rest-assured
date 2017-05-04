/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.service;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);

    Medium[] getBooks();
    Medium[] getDiscs();

    Medium getBook(String isbn);
    Medium getDisc(String barcode);

    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);
}
