package com.basaki.example.postgres.jsonb.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * {@code Genre} represents a genre of a {@code Book}.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/8/17
 */
public enum Genre {

    DRAMA, ROMANCE, GUIDE, TRAVEL;

    /**
     * Returns a <tt>Genre<tt> enum based on string matching
     *
     * @param value string stored in database
     * @return a matching <tt>Genre</tt>
     */
    @JsonCreator
    public static Genre fromValue(String value) {
        return valueOf(value.toUpperCase());
    }

    /**
     * Converts a <tt>Genre</tt> to matching type string
     *
     * @param genre service enum
     * @return matching type string
     */
    @JsonValue
    public static String toValue(Genre genre) {
        return genre.name().toLowerCase();
    }
}
