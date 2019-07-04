package ru.zdoher.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами")
@JdbcTest
@Import({AuthorDaoImpl.class})
class AuthorDaoImplTest {

    @Autowired
    private AuthorDaoImpl authorDao;

    @DisplayName(" проверка всего корретно")
    @Test
    void authorDaoAll() {
        List<Author> authorList = authorDao.getAll();

        assertThat(authorList).isNotNull()
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getId() != null && s.getId() > 0);
    }

    @DisplayName(" проверка id 1 корректа")
    @Test
    void authorGetById() {
        Author author = authorDao.getById(1);

        assertThat(author).isNotNull()
                .matches( s -> s.getId() == 1)
                .matches( s -> s.getName().equals("Анджей Сапковский"));
    }

}