package edu.hm.shareit.media;

import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public class Copy {

    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    public Medium getMedium() {
        return medium;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Copy{");
        sb.append("medium=").append(medium);
        sb.append(", owner='").append(owner).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return Objects.equals(medium, copy.medium) &&
                Objects.equals(owner, copy.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medium, owner);
    }
}
