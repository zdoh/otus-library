package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс authorRepository")
@DataJpaTest
@Import({AuthorRepositoryImpl.class})
class AuthorRepositoryImplTest {
    private static final Long FIRST_ID = 1L ;
    private static final String FIRST_NAME = "Анджей Сапковский";
    private static final Long NEW_ID = 3L;
    private static final String NEW_NAME = "author3";
    private static final int NEW_SIZE = 2;
    private static final String NEW_FIRST_NAME = "author1";

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @DisplayName(" проверка получения всего - корректно")
    @Test
    void authorDaoAll() {
        List<Author> authorList = authorRepository.getAll();

        assertThat(authorList).isNotNull()
                .hasSize(2)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getId() != null && s.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 - корректно")
    @Test
    void authorGetById() {
        Author author = authorRepository.getById(FIRST_ID);

        assertThat(author)
                .matches( s -> s.getId().equals(FIRST_ID))
                .matches( s -> s.getName().equals(FIRST_NAME));
    }

    @DisplayName(" проверка вставки нового автора - корректно")
    @Test
    void authorAdd() {
        authorRepository.insert(new Author(NEW_NAME));

        Author author = authorRepository.getById(NEW_ID);

        assertThat(author)
                .matches( s -> s.getId().equals(NEW_ID))
                .matches( s -> s.getName().equals(NEW_NAME));
    }

    @DisplayName(" проверка удаления автора - корректно")
    @Test
    void authorDelete() {
        authorRepository.deleteById(NEW_ID);

        List<Author> authorList = authorRepository.getAll();

        assertThat(authorList.size()).isEqualTo(NEW_SIZE);

    }

    @DisplayName(" проверка редактирование автора - корректно")
    @Test
    void authorRename() {
        Author authorOld = authorRepository.getById(FIRST_ID);
        authorOld.setName(NEW_FIRST_NAME);

        authorRepository.update(authorOld);

        Author authorNew = authorRepository.getById(FIRST_ID);

        assertThat(authorNew)
                .matches( s -> s.getName().equals(NEW_FIRST_NAME));
    }
}