package edu.hm.shareit.storage;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
     *
     * @return default static MediaStorage.
     */
    public static MediaStorage getDefault() {
        return INSTANCE;
    }

    /**
     * Hibernate Session.
     */
    private final Session entityManager;

    /**
     * Create new MediaStorage.
     */
    public MediaStorage() {
        final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        this.entityManager = sessionFactory.openSession();
    }

    /**
     * Add new Book to storage.
     *
     * @param book New Book.
     */
    public void addBook(Book book) {
        Transaction tx = entityManager.beginTransaction();
        entityManager.persist(book);
        tx.commit();
    }

    /**
     * Check if storage contains book for given ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if book available, false otherwise.
     */
    public boolean containsBook(String isbn) {
        return getBook(isbn) != null;
    }

    /**
     * Return a copy of all Books.
     *
     * @return All Books.
     */
    public List<Book> getBooks() {
        Transaction tx = entityManager.beginTransaction();
        final String queryString = "from Book";
        final Query<Book> query = entityManager.createQuery(queryString);
        final List<Book> result = query.list();
        tx.commit();
        return result;
    }

    /**
     * Get Book for ISBN.
     *
     * @param isbn Given ISBN.
     * @return Book if available, null otherwise.
     */
    public Book getBook(String isbn) {
        Transaction tx = entityManager.beginTransaction();
        final Book book = entityManager.get(Book.class, isbn);
        tx.commit();
        return book;
    }

    /**
     * Remove Book from storage by ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if a Book is removed, false if none existed for given ISBN.
     */
    public boolean removeBook(String isbn) {
        final Book book = entityManager.load(Book.class, isbn);
        entityManager.delete(book);
        entityManager.flush();
        return book != null;
    }

    // Discs

    /**
     * Add new Disc to storage.
     *
     * @param disc New Disk.
     */
    public void addDisc(Disc disc) {
        Transaction tx = entityManager.beginTransaction();
        entityManager.persist(disc);
        tx.commit();
    }

    /**
     * Check if storage contains Disc for given barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc available, false otherwise.
     */
    public boolean containsDisc(String barcode) {
        return getDisc(barcode) != null;
    }

    /**
     * Returns a copy list of all Discs.
     *
     * @return Copy List of all Discs.
     */
    public List<Disc> getDiscs() {
        Transaction tx = entityManager.beginTransaction();
        final String queryString = "from Disc";
        final Query<Disc> query = entityManager.createQuery(queryString);
        final List<Disc> result = query.list();
        tx.commit();
        return result;
    }

    /**
     * Get Disc for Barcode.
     *
     * @param barcode Given Barcode.
     * @return Disc if available, null otherwise.
     */
    public Disc getDisc(String barcode) {
        Transaction tx = entityManager.beginTransaction();
        final Disc disc = entityManager.get(Disc.class, barcode);
        tx.commit();
        return disc;
    }

    /**
     * Remove Disc from storage by Barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc was removed, false if none was available for given Barcode.
     */
    public boolean removeDisc(String barcode) {
        final Disc disc = entityManager.load(Disc.class, barcode);
        entityManager.delete(disc);
        entityManager.flush();
        return disc != null;
    }

    @Override
    public String toString() {
        return "MediaStorage [entityManager=" + entityManager + "]";
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
        return Objects.equals(entityManager, other.entityManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityManager);
    }
}
