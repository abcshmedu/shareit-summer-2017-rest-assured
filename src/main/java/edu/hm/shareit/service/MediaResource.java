/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.service;

import edu.hm.shareit.media.Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 03.05.17
 */
public class MediaResource {

    private final List<Medium> media;

    public MediaResource() {
        this.media = new LinkedList<>();
    }

}
