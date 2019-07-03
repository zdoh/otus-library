package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Author")
class AuthorTest {

    @Test
    @DisplayName(" корректно создается")
    void authorCreateAndGet() {
        Author author = new Author(1, "author");

        assertAll(
                () -> assertEquals(Integer.valueOf(1), author.getId()),
                () -> assertEquals("author", author.getName())
        );
    }

}