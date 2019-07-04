package ru.zdoher.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс authorDao")
@JdbcTest
@Import({AuthorDaoImpl.class})
class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @DisplayName(" проверка получения всего корретно")
    @Test
    void authorDaoAll() {
        List<Author> authorList = authorDao.getAll();

        assertThat(authorList).isNotNull()
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getId() != null && s.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 корректа")
    @Test
    void authorGetById() {
        Author author = authorDao.getById(1);

        assertThat(author)
                .matches( s -> s.getId() == 1)
                .matches( s -> s.getName().equals("Анджей Сапковский"));
    }

    @DisplayName(" проверка вставки нового автора корректна")
    @Test
    void authorAdd() {
        authorDao.insert(new Author(null, "author3"));

        Author author = authorDao.getById(3);

        assertThat(author)
                .matches( s -> s.getId() == 3)
                .matches( s -> s.getName().equals("author3"));
    }

    @DisplayName(" проверка удаления автора корректна")
    @Test
    void authorDelete() {
        authorDao.deleteById(2);

        List<Author> authorList = authorDao.getAll();

        assertThat(authorList.size()).isEqualTo(1);

    }

    @DisplayName(" проверка редактирование автора корректна")
    @Test
    void authorRename() {
        authorDao.update(new Author(1, "author1"));

        Author author = authorDao.getById(1);

        assertThat(author)
                .matches( s -> s.getName().equals("author1"));
    }
}