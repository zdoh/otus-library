package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Comment")
@DataJpaTest
class CommentTest {


    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName(" корректно создается")
    void authorCreateAndGet() {
        Author author = new Author("authorName");
        Genre genre = new Genre("genreName");
        Book book = new Book("bookName", author, genre);
        Comment comment = new Comment("superComment", book);
        em.persist(author);
        em.persist(genre);
        em.persist(book);

        Comment fromDb = em.persistAndFlush(comment);

        assertAll(
                () -> assertThat(fromDb.getId()).isNotZero(),
                () -> assertThat(fromDb.getComment()).isEqualTo(comment.getComment()),
                () -> assertThat(fromDb.getBook().getName()).isEqualTo(book.getName()),
                () -> assertThat(fromDb.getBook().getGenre().getName()).isEqualTo(genre.getName()),
                () -> assertThat(fromDb.getBook().getAuthor().getName()).isEqualTo(author.getName())
        );


    }
}