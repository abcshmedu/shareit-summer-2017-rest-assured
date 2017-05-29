package edu.hm.shareit.model;

import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public abstract class Medium {

    /**
     * The Medium's title.
     */
    private final String title;

    /**
     * Create new Medium with given title.
     *
     * @param title Given title.
     */
    public Medium(String title) {
        this.title = title;
    }

    /**
     * Get title.
     *
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medium{");
        sb.append("title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medium that = (Medium) obj;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}
