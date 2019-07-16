package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Author")
@DataJpaTest
class AuthorTest {

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName(" корректно создается")
    void authorCreateAndGet() {
        Author author = new Author("author");

        Author fromDb = em.persistAndFlush(author);
        assertAll(
                () -> assertThat(fromDb.getId()).isNotZero(),
                () -> assertThat(fromDb.getName()).isEqualTo(author.getName())
        );
    }

}