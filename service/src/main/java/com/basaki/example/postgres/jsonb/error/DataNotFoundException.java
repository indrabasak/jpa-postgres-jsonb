package com.basaki.example.postgres.jsonb.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code DataNotFoundException} is a runtime exception if no entities are found
 * in the database.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/18/17
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String s) {
        super(s);
    }
}
