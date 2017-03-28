package com.basaki.example.postgres.jsonb.data.entity;

import com.basaki.example.postgres.jsonb.model.Author;
import com.basaki.example.postgres.jsonb.model.Genre;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * {@code BookRecord} represents a row in the <code>Books</code> database table.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/13/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books", schema = "example_jsonb")
public class BookEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    @Type(type = "com.basaki.example.postgres.jsonb.data.usertype.PGEnumUserType",
            parameters = {@Parameter(name = "enumClassName",
                    value = "com.basaki.example.postgres.jsonb.model.Genre")})
    private Genre genre;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "star")
    private int star;

    @Column(name = "author", nullable = false)
    @Type(type = "com.basaki.example.postgres.jsonb.data.usertype.JsonbUserType",
            parameters = {@Parameter(name = "className",
                    value = "com.basaki.example.postgres.jsonb.model.Author")})
    private Author author;
}
