package com.basaki.example.postgres.jsonb.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code ErrorInfo} represents an error response object which is exposed to
 * the external client in a human readbable form.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/18/17
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorInfo {

    private String path;

    private int code;

    private String type;

    private String message;
}