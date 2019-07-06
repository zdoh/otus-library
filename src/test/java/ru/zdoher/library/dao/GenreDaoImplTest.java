package ru.zdoher.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс GenreDao")
@JdbcTest
@Import({GenreDaoImpl.class})
class GenreDaoImplTest {
    private final Long FIRST_ID = 1L;
    private final String FIRST_GENRE_NAME = "фэнтези";
    private final Long NEW_ID = 3L;
    private final String NEW_GENRE_NAME = "genre3";
    private final int NEW_SIZE = 1;
    private final String NEW_FIRST_NAME = "genre1";

    @Autowired
    private GenreDao genreDao;

    @DisplayName(" проверка получения всего корретно")
    @Test
    void genreDaoAll() {
        List<Genre> genreList = genreDao.getAll();

        assertThat(genreList).isNotNull()
                .allMatch(g -> !g.getName().equals(""))
                .allMatch(g -> g.getId() != null && g.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 корректа")
    @Test
    void genreGetById() {
        Genre genre = genreDao.getById(FIRST_ID);

        assertThat(genre)
                .matches( g -> g.getId().equals(FIRST_ID))
                .matches( g -> g.getName().equals(FIRST_GENRE_NAME));
    }

    @DisplayName(" проверка вставки нового жанра корректна")
    @Test
    void genreAdd() {
        genreDao.insert(new Genre(NEW_ID, NEW_GENRE_NAME));

        Genre genre = genreDao.getById(NEW_ID);

        assertThat(genre)
                .matches( g -> g.getId().equals(NEW_ID))
                .matches( g -> g.getName().equals(NEW_GENRE_NAME));
    }

    @DisplayName(" проверка удаления жанра корректна")
    @Test
    void genreDelete() {
        genreDao.deleteById(FIRST_ID);

        List<Genre> genreList = genreDao.getAll();

        assertThat(genreList.size()).isEqualTo(NEW_SIZE);

    }

    @DisplayName(" проверка редактирование жанра корректна")
    @Test
    void genreRename() {
        genreDao.update(new Genre(FIRST_ID, NEW_FIRST_NAME));

        Genre genre = genreDao.getById(FIRST_ID);

        assertThat(genre)
                .matches( g -> g.getName().equals(NEW_FIRST_NAME));
    }
}