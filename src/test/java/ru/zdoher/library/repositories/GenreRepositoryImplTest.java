package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс GenreRepository")
@DataJpaTest
@Import({GenreRepositoryImpl.class})
class GenreRepositoryImplTest {
    private static final Long FIRST_ID = 1L;
    private static final String FIRST_GENRE_NAME = "фэнтези";
    private static final String NEW_GENRE_NAME = "genre3";
    private static final int NEW_SIZE = 1;
    private static final String NEW_FIRST_NAME = "genre1";

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" проверка получения всего корретно")
    @Test
    void genreDaoAll() {
        List<Genre> genreList = genreRepository.getAll();

        assertThat(genreList).isNotNull()
                .hasSize(2)
                .allMatch(g -> !g.getName().equals(""))
                .allMatch(g -> g.getId() != null && g.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 корректа")
    @Test
    void genreGetById() {
        Genre genre = genreRepository.getById(FIRST_ID);

        assertThat(genre)
                .matches( g -> g.getId().equals(FIRST_ID))
                .matches( g -> g.getName().equals(FIRST_GENRE_NAME));
    }

    @DisplayName(" проверка вставки нового жанра корректна")
    @Test
    void genreAdd() {
        Genre genreOld = genreRepository.getById(FIRST_ID);
        genreOld.setName(NEW_GENRE_NAME);

        genreRepository.insert(genreOld);

        Genre genreNew = genreRepository.getById(FIRST_ID);

        assertThat(genreNew)
                .matches( g -> g.getId().equals(FIRST_ID))
                .matches( g -> g.getName().equals(NEW_GENRE_NAME));
    }

    @DisplayName(" проверка удаления жанра корректна")
    @Test
    void genreDelete() {
        genreRepository.deleteById(FIRST_ID);

        List<Genre> genreList = genreRepository.getAll();

        assertThat(genreList.size()).isEqualTo(NEW_SIZE);

    }

    @DisplayName(" проверка редактирование жанра корректна")
    @Test
    void genreRename() {
        Genre genreOld = genreRepository.getById(FIRST_ID);
        genreOld.setName(NEW_FIRST_NAME);
        genreRepository.update(genreOld);

        Genre genre = genreRepository.getById(FIRST_ID);

        assertThat(genre)
                .matches( g -> g.getName().equals(NEW_FIRST_NAME));
    }
}