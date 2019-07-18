package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.domain.Genre;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс CommentRepository")
@DataJpaTest
@Transactional
class CommentRepositoryTest {
    private static final String NEW_AUTHOR_NAME = "newAuthor";
    private static final String NEW_GENRE_NAME = "newGenre";
    private static final String NEW_BOOK = "newBook";
    private static final String NEW_COMMENT = "newComment";

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" создание и получение - корректно")
    @Test
    void addCommentToBookAndGet() {
        final Author newAuthor = em.persist(new Author(NEW_AUTHOR_NAME));
        final Genre newGenre = em.persist(new Genre(NEW_GENRE_NAME));
        final Book newBook = em.persist(new Book(NEW_BOOK, newAuthor, newGenre));
        final Comment newComment = em.persist(new Comment(NEW_COMMENT, newBook));

        Comment comment = commentRepository.findById(newComment.getId()).orElse(null);

        assertThat(comment).isNotNull()
                .matches( c -> c.getComment().equals(NEW_COMMENT))
                .matches( c -> c.getId().equals(newComment.getId()))
                .matches( c -> c.getBook().getName().equals(newComment.getBook().getName()))
                .matches( c -> c.getBook().getAuthor().getName().equals(newComment.getBook().getAuthor().getName()))
                .matches( c -> c.getBook().getGenre().getName().equals(newComment.getBook().getGenre().getName()));
    }

}
