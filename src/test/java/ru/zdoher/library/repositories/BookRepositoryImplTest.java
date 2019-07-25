package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Class BookRepository")
@DataMongoTest
@ComponentScan({"ru.zdoher.library.events"})
class BookRepositoryImplTest {
    private static final String NEW_AUTHOR_NAME1 = "newAuthor1";
    private static final String NEW_AUTHOR_NAME2 = "newAuthor2";
    private static final String NEW_GENRE_NAME1 = "newGenre1";
    private static final String NEW_GENRE_NAME2 = "newGenre2";
    private static final String NEW_BOOK1 = "newBook1";
    private static final String NEW_BOOK2 = "newBook2";
    private static final String NEW_BOOK3 = "newBook3";
    private static final String NEW_BOOK4 = "newBook4";
    private static final int COUNT_NEW_AUTHOR_NAME1 = 3;
    private static final int COUNT_NEW_GENRE_NAME1 = 2;


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;



    @DisplayName(" create and get - correct")
    @Test
    void bookCreateAndGet() {
        final Author newAuthor = authorRepository.insert(new Author(NEW_AUTHOR_NAME1));
        final Genre newGenre = genreRepository.insert(new Genre(NEW_GENRE_NAME1));
        final Book newBook = bookRepository.insert(new Book(NEW_BOOK1, newAuthor, newGenre));

        Book book = bookRepository.findById(newBook.getId()).orElse(null);

        assertThat(book).isNotNull()
                .matches( c -> c.getName().equals(newBook.getName()))
                .matches( c -> c.getId().equals(newBook.getId()))
                .matches( c -> c.getAuthor().getName().equals(newBook.getAuthor().getName()))
                .matches( c -> c.getGenre().getName().equals(newBook.getGenre().getName()));
    }

    @DisplayName(" countBookByAuthorId - correct")
    @Test
    void checkCountBookByAuthorId() {
        final Author newAuthor1 = authorRepository.insert(new Author(NEW_AUTHOR_NAME1));
        final Author newAuthor2 = authorRepository.insert(new Author(NEW_AUTHOR_NAME2));
        final Genre newGenre1 = genreRepository.insert(new Genre(NEW_GENRE_NAME1));
        final Genre newGenre2 = genreRepository.insert(new Genre(NEW_GENRE_NAME2));
        bookRepository.insert(new Book(NEW_BOOK1, newAuthor1, newGenre1));
        bookRepository.insert(new Book(NEW_BOOK2, newAuthor1, newGenre1));
        bookRepository.insert(new Book(NEW_BOOK3, newAuthor1, newGenre2));
        bookRepository.insert(new Book(NEW_BOOK4, newAuthor2, newGenre2));

        int countNewAuthor1 = bookRepository.countBookByAuthorId(newAuthor1.getId());

        assertAll(
                () -> assertTrue(countNewAuthor1 == COUNT_NEW_AUTHOR_NAME1)
        );
    }


    @DisplayName(" countBookByGenreId - correct")
    @Test
    void checkCountBookByGenreId() {
        final Author newAuthor1 = authorRepository.insert(new Author(NEW_AUTHOR_NAME1));
        final Author newAuthor2 = authorRepository.insert(new Author(NEW_AUTHOR_NAME2));
        final Genre newGenre1 = genreRepository.insert(new Genre(NEW_GENRE_NAME1));
        final Genre newGenre2 = genreRepository.insert(new Genre(NEW_GENRE_NAME2));
        bookRepository.insert(new Book(NEW_BOOK1, newAuthor1, newGenre1));
        bookRepository.insert(new Book(NEW_BOOK2, newAuthor1, newGenre1));
        bookRepository.insert(new Book(NEW_BOOK3, newAuthor1, newGenre2));
        bookRepository.insert(new Book(NEW_BOOK4, newAuthor2, newGenre2));

        int countNewAuthor1 = bookRepository.countBookByGenreId(newGenre1.getId());

        assertAll(
                () -> assertTrue(countNewAuthor1 == COUNT_NEW_GENRE_NAME1)
        );
    }

}
