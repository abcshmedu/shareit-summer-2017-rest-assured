package edu.hm.shareit.media;

import java.util.Objects;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 19.04.17
 */
public class Copy {

    /**
     * Medium of which this is a copy.
     */
    private final Medium medium;
    /**
     * The Copy's owner.
     */
    private final String owner;

    /**
     * Create new Copy for given Medium and owner.
     *
     * @param medium Copy's Medium.
     * @param owner  Copy's owner.
     */
    public Copy(Medium medium, String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    /**
     * Get Medium.
     *
     * @return Medium.
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * Get Owner.
     *
     * @return Owner string.
     */
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Copy copy = (Copy) o;
        return Objects.equals(medium, copy.medium)
               && Objects.equals(owner, copy.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medium, owner);
    }
}
