package edu.hm.shareit.storage;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 04.05.17
 */
public final class MediaStorage {

    private static final MediaStorage INSTANCE = new MediaStorage();

    public static MediaStorage getDefault() {
        return INSTANCE;
    }

    private final List<Book> books;
    private final List<Disc> discs;

    public MediaStorage() {
        this.books = new LinkedList<>();
        this.discs = new LinkedList<>();
    }

    // Books

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean containsBook(String isbn) {
        return books.stream().anyMatch(b -> b.getIsbn().equals(isbn));
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public Book getBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public boolean removeBook(String isbn) {
        return books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    // Discs

    public void addDisc(Disc disc) {
        discs.add(disc);
    }
    
    public boolean containsDisc(String barcode) {
        return discs.stream().anyMatch(b -> b.getBarcode().equals(barcode));
    }

    public List<Disc> getDiscs() {
        return Collections.unmodifiableList(discs);
    }

    public Disc getDisc(String barcode) {
        return discs.stream().filter(d -> d.getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    public boolean removeDisc(String barcode) {
        return discs.removeIf(d -> d.getBarcode().equals(barcode));
    }

	@Override
	public String toString() {
		return "MediaStorage [books=" + books + ", discs=" + discs + "]";
	}
}
