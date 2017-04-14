package com.basaki.example.postgres.jsonb.controller;

import com.basaki.example.postgres.jsonb.model.Author;
import com.basaki.example.postgres.jsonb.model.Book;
import com.basaki.example.postgres.jsonb.model.BookRequest;
import com.basaki.example.postgres.jsonb.model.Genre;
import com.basaki.example.postgres.jsonb.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * {@code BookControllerUnitTests} unit test class for {@code BookController}.
 * <p/>
 *
 * @author Indra Basak
 * @since 4/14/17
 */
public class BookControllerUnitTests {

    @Mock
    private BookService service;

    @InjectMocks
    private BookController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Book book = getBook();
        when(service.create(any(BookRequest.class))).thenReturn(book);

        Book returnedObj = controller.create(new BookRequest());
        validate(book, returnedObj);
    }

    @Test
    public void testGetById() {
        Book book = getBook();
        when(service.getById(any(UUID.class))).thenReturn(book);

        Book returnedObj = controller.getById(book.getId());
        validate(book, returnedObj);
    }

    @Test
    public void testGet() {
        Book book = getBook();
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(service.get(any(String.class), any(Genre.class), any(String.class),
                any(Author.class))).thenReturn(books);

        List<Book> returnedObjs =
                controller.get(book.getTitle(), book.getGenre(),
                        book.getPublisher());
        assertEquals(1, returnedObjs.size());
        validate(book, returnedObjs.get(0));
    }

    @Test
    public void testGetByAuthor() {
        Book book = getBook();
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(service.getByAuthor(any(String.class),
                any(String.class))).thenReturn(books);

        List<Book> returnedObjs =
                controller.getByAuthor("john", "doe");
        assertEquals(1, returnedObjs.size());
        validate(book, returnedObjs.get(0));
    }

    @Test
    public void testUpdate() {
        Book book = getBook();
        when(service.update(any(UUID.class),
                any(BookRequest.class))).thenReturn(book);

        Book returnedObj =
                controller.update(UUID.randomUUID(), new BookRequest());
        validate(book, returnedObj);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(service).delete(any(UUID.class));

        controller.deleteById(UUID.randomUUID());
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(service).deleteAll();

        controller.deleteAll();
    }

    @Test
    public void testGetPublisher() {
        List<String> publishers = new ArrayList<>();
        publishers.add("Scribner's");
        when(service.getPublisher(any(String.class))).thenReturn(publishers);

        List<String> returnedObjs = controller.getPublisher("a");
        assertEquals(1, returnedObjs.size());
        assertEquals(publishers.get(0), returnedObjs.get(0));
    }

    private void validate(Book expected, Book actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getPublisher(), actual.getPublisher());

        assertNotNull(expected.getAuthor());
        assertNotNull(actual.getAuthor());
        assertEquals(expected.getAuthor().getFirstName(),
                actual.getAuthor().getFirstName());
        assertEquals(expected.getAuthor().getLastName(),
                actual.getAuthor().getLastName());
    }

    private Book getBook() {
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("Ethan Frome");
        book.setGenre(Genre.DRAMA);
        book.setPublisher("Scribner's");
        book.setStar(5);

        Author author = new Author();
        author.setFirstName("Edith");
        author.setLastName("Wharton");
        book.setAuthor(author);

        return book;
    }
}
