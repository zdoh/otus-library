package ru.zdoher.library.dao;

import org.junit.Ignore;
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
        Genre genre = genreDao.getById(1L);

        assertThat(genre)
                .matches( g -> g.getId() == 1)
                .matches( g -> g.getName().equals("фэнтези"));
    }

    @DisplayName(" проверка вставки нового жанра корректна")
    @Test
    void genreAdd() {
        genreDao.insert(new Genre(3L, "genre3"));

        Genre genre = genreDao.getById(3L);

        assertThat(genre)
                .matches( g -> g.getId() == 3L)
                .matches( g -> g.getName().equals("genre3"));
    }

    @DisplayName(" проверка удаления жанра корректна")
    @Test
    void genreDelete() {
        genreDao.deleteById(2L);

        List<Genre> genreList = genreDao.getAll();

        assertThat(genreList.size()).isEqualTo(1);

    }

    @DisplayName(" проверка редактирование жанра корректна")
    @Test
    void genreRename() {
        genreDao.update(new Genre(1L, "genre1"));

        Genre genre = genreDao.getById(1L);

        assertThat(genre)
                .matches( g -> g.getName().equals("genre1"));
    }
}