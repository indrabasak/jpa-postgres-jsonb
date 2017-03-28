package com.basaki.example.postgres.jsonb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code Book} represents an author.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/13/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private String firstName;

    private String lastName;
}
