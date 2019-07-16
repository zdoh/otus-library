package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Genre")
@DataJpaTest
class GenreTest {

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName(" корректно создается")
    void genreCreateAndGet() {
        Genre genre = new Genre("genre");

        Genre fromDb = em.persistAndFlush(genre);

        assertAll(
                () -> assertThat(fromDb.getId()).isNotZero(),
                () -> assertThat(fromDb.getName()).isEqualTo(genre.getName())
        );

    }
}