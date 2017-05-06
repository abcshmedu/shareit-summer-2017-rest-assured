/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.service;

import java.util.List;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.storage.MediaStorage;
import edu.hm.shareit.util.Validator;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 25.04.17
 */
public class MediaServiceImpl implements MediaService {

    private final MediaStorage mediaStorage = new MediaStorage();
    
    public MediaStorage getMediaStorage() {
		return mediaStorage;
	}

	@Override
    public MediaServiceResult addBook(Book book) {
    	if(book.getTitle().equals("")) {
    		return MediaServiceResult.MISSING_TITLE;
    	}
    	if(book.getAuthor().equals("")) {
    		return MediaServiceResult.MISSING_AUTHOR;
    	}
    	if(!Validator.isValidIsbn(book.getIsbn())) {
    		return MediaServiceResult.INVALID_ISBN;
    	}
        if(mediaStorage.containsBook(book.getIsbn())) {
            return MediaServiceResult.ISBN_ALREADY_IN_USE;
        }
        mediaStorage.addBook(book);
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
    	List<Book> bookList = mediaStorage.getBooks();
    	return bookList.toArray(new Medium[bookList.size()]);
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Override
    public Medium getBook(String isbn) {
    	if(!Validator.isValidIsbn(isbn)) {
    		return null;
    	}
    	if(mediaStorage.containsBook(isbn)) {
    		return mediaStorage.getBook(isbn);
    	}
    	return null;
    }

    @Override
    public Medium getDisc(String barcode) {
        return null;
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
    	if(book.getTitle().equals("")) {
    		return MediaServiceResult.MISSING_TITLE;
    	}
    	if(book.getAuthor().equals("")) {
    		return MediaServiceResult.MISSING_AUTHOR;
    	}
    	if(!Validator.isValidIsbn(book.getIsbn())) {
    		return MediaServiceResult.INVALID_ISBN;
    	}
    	if(mediaStorage.containsBook(book.getIsbn())) {
    		mediaStorage.removeBook(book.getIsbn());
    		mediaStorage.addBook(book);
    		return MediaServiceResult.OK;
    	}
    	return MediaServiceResult.ISBN_NOT_FOUND;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
