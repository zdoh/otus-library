package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.zdoher.library.domain.Genre;

import javax.transaction.Transactional;


import static org.assertj.core.api.Assertions.assertThat;



@DisplayName("Класс GenreRepository")
@DataJpaTest
@Transactional
class GenreRepositoryImplTest {
    private static final String NEW_GENRE_NAME = "genre3";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName(" создание и получение - корректно")
    void genreCreateAndGet() {
        final Genre newGenre = em.persist(new Genre(NEW_GENRE_NAME));

        Genre author = genreRepository.findById(newGenre.getId()).orElse(null);

        assertThat(author).isNotNull()
                .matches(s -> s.getName().equals(newGenre.getName()))
                .matches(s -> s.getId().equals(newGenre.getId()));

    }
}