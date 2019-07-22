package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.zdoher.library.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс authorRepository")
@DataMongoTest
class AuthorRepositoryTest {
    private static final String NEW_NAME = "newAuthor";

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName(" создание и получение - корректно")
    void authorCreateAndGet() {
        final Author newAuthor = authorRepository.insert(new Author(NEW_NAME));

        Author author = authorRepository.findById(newAuthor.getId()).orElse(null);

        assertThat(author).isNotNull()
                .matches(s -> s.getName().equals(newAuthor.getName()))
                .matches(s -> s.getId().equals(newAuthor.getId()));
    }
}
