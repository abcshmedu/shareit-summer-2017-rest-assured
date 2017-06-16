package edu.hm.shareit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium implements Serializable {

    /**
     * The Medium's title.
     */
    private final String title;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

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
