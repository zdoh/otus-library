package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Class BookRepository")
@DataMongoTest
@ComponentScan({"ru.zdoher.library.events"})
class BookRepositoryImplTest {
    private static final String NEW_AUTHOR_NAME = "newAuthor";
    private static final String NEW_GENRE_NAME = "newGenre";
    private static final String NEW_BOOK = "newBook";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" create and get - correct")
    @Test
    void bookCreateAndGet() {
        final Author newAuthor = authorRepository.insert(new Author(NEW_AUTHOR_NAME));
        final Genre newGenre = genreRepository.insert(new Genre(NEW_GENRE_NAME));
        final Book newBook = bookRepository.insert(new Book(NEW_BOOK, newAuthor, newGenre));

        Book book = bookRepository.findById(newBook.getId()).orElse(null);

        assertThat(book).isNotNull()
                .matches( c -> c.getName().equals(newBook.getName()))
                .matches( c -> c.getId().equals(newBook.getId()))
                .matches( c -> c.getAuthor().getName().equals(newBook.getAuthor().getName()))
                .matches( c -> c.getGenre().getName().equals(newBook.getGenre().getName()));
    }


}
