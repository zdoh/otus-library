package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.zdoher.library.domain.Author;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс authorRepository")
@DataJpaTest
@Transactional
class AuthorRepositoryTest {
    private static final String NEW_NAME = "newAuthor";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName(" создание и получение - корректно")
    void authorCreateAndGet() {
        final Author newAuthor = em.persist(new Author(NEW_NAME));

        Author author = authorRepository.findById(newAuthor.getId()).orElse(null);

        assertThat(author).isNotNull()
                .matches(s -> s.getName().equals(newAuthor.getName()))
                .matches(s -> s.getId().equals(newAuthor.getId()));
    }
}
