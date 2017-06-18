package edu.hm.shareit.storage;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * MediaStorageImpl for saving Books and Discs.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 04.05.17
 */
public final class MediaStorageHibernate implements MediaStorage {

    private static final MediaStorage INSTANCE = new MediaStorageHibernate();

    /**
     * Get the static default MediaStorageImpl.
     *
     * @return default static MediaStorageImpl.
     */
    public static MediaStorage getDefault() {
        return INSTANCE;
    }

    /**
     * Hibernate Session.
     */
    private final SessionFactory sessionFactory;

    /**
     * Create new MediaStorageImpl.
     */
    public MediaStorageHibernate() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Add new Book to storage.
     *
     * @param book New Book.
     */
    @Override
    public void addBook(Book book) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * Check if storage contains book for given ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if book available, false otherwise.
     */
    @Override
    public boolean containsBook(String isbn) {
        return getBook(isbn) != null;
    }

    /**
     * Return a copy of all Books.
     *
     * @return All Books.
     */
    @Override
    public List<Book> getBooks() {
        Transaction tx = null;
        List<Book> books = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            final Query<Book> query = session.createQuery("from Book");
            books = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return books == null ? new LinkedList<>() : books;
    }

    /**
     * Get Book for ISBN.
     *
     * @param isbn Given ISBN.
     * @return Book if available, null otherwise.
     */
    @Override
    public Book getBook(String isbn) {
        Transaction tx = null;
        Book book = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            final Query<Book> query = session.createQuery("from Book where isbn='" + isbn + "'");
            final List<Book> result = query.list();
            if (result.size() == 1) {
                book = result.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return book;
    }

    /**
     * Remove Book from storage by ISBN.
     *
     * @param isbn Given ISBN.
     * @return true if a Book is removed, false if none existed for given ISBN.
     */
    @Override
    public boolean removeBook(String isbn) {
        Transaction tx = null;
        final Book book = getBook(isbn);
        if (book == null) {
            return false;
        }
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    // Discs

    /**
     * Add new Disc to storage.
     *
     * @param disc New Disk.
     */
    @Override
    public void addDisc(Disc disc) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(disc);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * Check if storage contains Disc for given barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc available, false otherwise.
     */
    @Override
    public boolean containsDisc(String barcode) {
        return getDisc(barcode) != null;
    }

    /**
     * Returns a copy list of all Discs.
     *
     * @return Copy List of all Discs.
     */
    @Override
    public List<Disc> getDiscs() {
        Transaction tx = null;
        List<Disc> discs = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            final Query<Disc> query = session.createQuery("from Disc");
            discs = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return discs == null ? new LinkedList<>() : discs;
    }

    /**
     * Get Disc for Barcode.
     *
     * @param barcode Given Barcode.
     * @return Disc if available, null otherwise.
     */
    @Override
    public Disc getDisc(String barcode) {
        Transaction tx = null;
        Disc disc = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            final Query<Disc> query = session.createQuery("from Disc where barcode='" + barcode + "'");
            final List<Disc> result = query.list();
            if (result.size() == 1) {
                disc = result.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return disc;
    }

    /**
     * Remove Disc from storage by Barcode.
     *
     * @param barcode Given Barcode.
     * @return true if Disc was removed, false if none was available for given Barcode.
     */
    @Override
    public boolean removeDisc(String barcode) {
        Transaction tx = null;
        final Disc disc = getDisc(barcode);
        if (disc == null) {
            return false;
        }
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(disc);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MediaStorageImpl [sessionFactory=" + sessionFactory + "]";
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
        MediaStorageHibernate other = (MediaStorageHibernate) obj;
        return Objects.equals(sessionFactory, other.sessionFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionFactory);
    }
}
