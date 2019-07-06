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
    private final Long FIRST_ID = 1L;
    private final String FIRST_NAME = "Анджей Сапковский";
    private final Long NEW_ID = 3L;
    private final String NEW_NAME = "author3";
    private final int NEW_SIZE = 2;
    private final String NEW_FIRST_NAME = "author1";


    @Autowired
    private AuthorDaoImpl authorDao;

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
        Author author = authorDao.getById(FIRST_ID);

        assertThat(author)
                .matches( s -> s.getId().equals(FIRST_ID))
                .matches( s -> s.getName().equals(FIRST_NAME));
    }

    @DisplayName(" проверка вставки нового автора корректна")
    @Test
    void authorAdd() {
        authorDao.insert(new Author(null, NEW_NAME));

        List<Author> authorList = authorDao.getAll();

        authorList.forEach(s -> System.out.println(s.getId() + " " + s.getName()));

        Author author = authorDao.getById(NEW_ID);

        assertThat(author)
                .matches( s -> s.getId().equals(NEW_ID))
                .matches( s -> s.getName().equals(NEW_NAME));
    }

    @DisplayName(" проверка удаления автора корректна")
    @Test
    void authorDelete() {
        authorDao.deleteById(NEW_ID);

        List<Author> authorList = authorDao.getAll();

        assertThat(authorList.size()).isEqualTo(NEW_SIZE);

    }

    @DisplayName(" проверка редактирование автора корректна")
    @Test
    void authorRename() {
        authorDao.update(new Author(FIRST_ID, NEW_FIRST_NAME));

        Author author = authorDao.getById(FIRST_ID);

        assertThat(author)
                .matches( s -> s.getName().equals(NEW_FIRST_NAME));
    }
}