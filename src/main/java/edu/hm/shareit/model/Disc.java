package edu.hm.shareit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
@Entity
public class Disc extends Medium {

    /**
     * EAN-13 barcode. Used to identify discs.
     */
    private final String barcode;
    /**
     * The Disc's director.
     */
    private final String director;
    /**
     * The Disc's fsk.
     */
    private final int fsk;

    /**
     * Create new Disc with default values.
     */
    public Disc() {
        this("", "", "", 0);
    }

    /**
     * Create new Disc with given values.
     *
     * @param title    The Disc's title.
     * @param barcode  The Disc's barcode.
     * @param director The Disc's director.
     * @param fsk      The Disc's fsk.
     */
    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    /**
     * Get Barcode.
     *
     * @return Barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Get director.
     *
     * @return Director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Get fsk.
     *
     * @return Fsk.
     */
    public int getFsk() {
        return fsk;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Disc{");
        sb.append("title='").append(getTitle()).append('\'');
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append(", fsk=").append(fsk);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Disc disc = (Disc) o;
        return fsk == disc.fsk
                && Objects.equals(barcode, disc.barcode)
                && Objects.equals(director, disc.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), barcode, director, fsk);
    }
}
