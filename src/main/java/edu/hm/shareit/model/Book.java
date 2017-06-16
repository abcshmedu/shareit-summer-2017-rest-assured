package edu.hm.shareit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Book to be stored and used within ShareIt.
 *
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
@Entity
public class Book extends Medium {

    /**
     * The Book's author.
     */
    private final String author;
    /**
     * ISBN-13. Used to identify discs.
     */
    private final String isbn;

    /**
     * Create new Book with default values.
     */
    public Book() {
        this("", "", "");
    }

    /**
     * Create new Book with given values.
     *
     * @param title  The Book's title.
     * @param author The Book's author.
     * @param isbn   The Books's ISBN.
     */
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Get author.
     *
     * @return Author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get ISBN.
     *
     * @return ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("title='").append(getTitle()).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append('}');
        return sb.toString();
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
        if (!super.equals(obj)) {
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(author, book.author)
                && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, isbn);
    }
}
