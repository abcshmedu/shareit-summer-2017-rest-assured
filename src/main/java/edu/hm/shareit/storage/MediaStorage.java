package edu.hm.shareit.storage;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * MediaStorage for saving Books and Discs.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 04.05.17
 */
public final class MediaStorage {
    
    private static final MediaStorage INSTANCE = new MediaStorage();
    
    /**
     * Get the static default MediaStorage.
     * @return default static MediaStorage.
     */
    public static MediaStorage getDefault() {
        return INSTANCE;
    }
     
    /**
     * Saved books.
     */
    private final List<Book> books;
    /**
     * Saved discs.
     */
    private final List<Disc> discs;

    /**
     * Create new MediaStorage.
     */
    public MediaStorage() {
        this.books = new LinkedList<>();
        this.discs = new LinkedList<>();
    }

    /**
     * Add new Book to storage.
     *
     * @param book New Book.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Check if storage contains book for given ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if book available, false otherwise.
     */
    public boolean containsBook(String isbn) {
        return books.stream().anyMatch(b -> (b.getIsbn()).equals(isbn));
    }

    /**
     * Return a copy of all Books.
     *
     * @return All Books.
     */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    /**
     * Get Book for ISBN.
     *
     * @param isbn Given ISBN.
     * @return Book if available, null otherwise.
     */
    public Book getBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    /**
     * Remove Book from storage by ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if a Book is removed, false if none existed for given ISBN.
     */
    public boolean removeBook(String isbn) {
        return books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    // Discs

    /**
     * Add new Disc to storage.
     *
     * @param disc New Disk.
     */
    public void addDisc(Disc disc) {
        discs.add(disc);
    }

    /**
     * Check if storage contains Disc for given barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc available, false otherwise.
     */
    public boolean containsDisc(String barcode) {
        return discs.stream().anyMatch(b -> b.getBarcode().equals(barcode));
    }

    /**
     * Returns a copy list of all Discs.
     *
     * @return Copy List of all Discs.
     */
    public List<Disc> getDiscs() {
        return Collections.unmodifiableList(discs);
    }

    /**
     * Get Disc for Barcode.
     *
     * @param barcode Given Barcode.
     * @return Disc if available, null otherwise.
     */
    public Disc getDisc(String barcode) {
        return discs.stream().filter(d -> d.getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    /**
     * Remove Disc from storage by Barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc was removed, false if none was available for given Barcode.
     */
    public boolean removeDisc(String barcode) {
        return discs.removeIf(d -> d.getBarcode().equals(barcode));
    }

    @Override
    public String toString() {
        return "MediaStorage [books=" + books + ", discs=" + discs + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MediaStorage other = (MediaStorage) obj;
        return Objects.equals(books, other.books)
                && Objects.equals(discs, other.discs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, discs);
    }
}
