package edu.hm.shareit.media;

import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public class Disc extends Medium {

    /**
     * EAN-13 barcode. Used to identify discs.
     */
    private final String barcode;
    private final String director;
    private final int fsk;

    public Disc() {
    	this("", "", "", 0);
    }
    
    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDirector() {
        return director;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Disc disc = (Disc) o;
        return fsk == disc.fsk &&
                Objects.equals(barcode, disc.barcode) &&
                Objects.equals(director, disc.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), barcode, director, fsk);
    }
}
