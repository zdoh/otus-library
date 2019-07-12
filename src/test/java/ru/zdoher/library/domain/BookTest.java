package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Book ")
@DataJpaTest
class BookTest {

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName(" корректно создается")
    void bookCreateAndGet() {
        Author author = new Author("authorName");
        Genre genre = new Genre("genreName");

        Book book = new Book("bookName", author, genre);
        em.persist(author);
        em.persist(genre);

        Book fromDb = em.persistAndFlush(book);

        assertAll(
                () -> assertThat(fromDb.getId()).isNotZero(),
                () -> assertThat(fromDb.getName()).isEqualTo(book.getName()),
                () -> assertThat(fromDb.getAuthor().getName()).isEqualTo(author.getName()),
                () -> assertThat(fromDb.getGenre().getName()).isEqualTo(genre.getName())
        );


    }
}