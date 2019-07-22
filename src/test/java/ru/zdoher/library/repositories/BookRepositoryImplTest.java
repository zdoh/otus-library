package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Class BookRepository")
@DataMongoTest
@ComponentScan({"ru.zdoher.library.events"})
class BookRepositoryImplTest {
    private static final String NEW_AUTHOR_NAME = "newAuthor";
    private static final String NEW_GENRE_NAME = "newGenre";
    private static final String NEW_BOOK = "newBook";
    private static final String NEW_COMMENT = "newComment";
    private static final int COMMENT_COUNT = 3;
    private static final int COMMENT_COUNT_AFTER_DEL = 1;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    @DisplayName(" create and get - correct")
    @Test
    void existsCommentsByIdAndCommentsId() {
        final Author newAuthor = authorRepository.insert(new Author(NEW_AUTHOR_NAME));
        final Genre newGenre = genreRepository.insert(new Genre(NEW_GENRE_NAME));
        final Comment comment = commentRepository.insert(new Comment(NEW_COMMENT));
        final Book newBook = bookRepository.insert(new Book(NEW_BOOK, newAuthor, newGenre, comment));

        Book book = bookRepository.findById(newBook.getId()).orElseThrow(NullPointerException::new);

        assertAll(
                () -> assertTrue(bookRepository.existsCommentsByIdAndCommentsId(book.getId(), comment.getId()))
        );

        bookRepository.delete(book);
    }

    @DisplayName(" delete book also delete all comment for that book - correct")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldRemoveCommentFromBookExperienceWhenRemovingBook() {
        final Author newAuthor = authorRepository.insert(new Author(NEW_AUTHOR_NAME));
        final Genre newGenre = genreRepository.insert(new Genre(NEW_GENRE_NAME));
        final Comment comment1 = commentRepository.insert(new Comment(NEW_COMMENT));
        final Comment comment2 = commentRepository.insert(new Comment(NEW_COMMENT));
        final Comment comment3 = commentRepository.insert(new Comment(NEW_COMMENT));
        final Book newBook = bookRepository.insert(new Book(NEW_BOOK, newAuthor, newGenre, comment1, comment2));

        Book book = bookRepository.findById(newBook.getId()).orElseThrow(NullPointerException::new);

        assertAll(
                () -> assertEquals(COMMENT_COUNT, commentRepository.findAll().size())
        );

        bookRepository.deleteById(book.getId());

        assertAll(
                () -> assertEquals(COMMENT_COUNT_AFTER_DEL, commentRepository.findAll().size())
        );
    }

}
