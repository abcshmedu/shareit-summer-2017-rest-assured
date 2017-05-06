package edu.hm.shareit.media;

import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public class Book extends Medium {

    private final String author;
    /**
     * ISBN-13. Used to identify discs.
     */
    private final String isbn;
    
    public Book() {
    	this("", "", "");
    }

    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

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
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		if (!super.equals(obj)) return false;
		Book book = (Book) obj;
		return Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn);
	}

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, isbn);
    }
}
