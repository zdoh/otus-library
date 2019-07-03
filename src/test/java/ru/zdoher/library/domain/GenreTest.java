package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Genre")
class GenreTest {

    @Test
    @DisplayName(" корректно создается")
    void genreCreateAndGet() {
        Genre genre = new Genre(1, "genre");

        assertAll(
                () -> assertEquals(Integer.valueOf(1), genre.getId()),
                () -> assertEquals("genre", genre.getName())
        );

    }
}