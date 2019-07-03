package ru.zdoher.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Book ")
class BookTest {

    @Test
    @DisplayName(" корректно создается")
    void bookCreateAndGet() {
        Book book = new Book(1, "bookName", "authorName", "genreName");

        assertAll(
                () -> assertEquals(Integer.valueOf(1), book.getId()),
                () -> assertEquals("bookName", book.getName()),
                () -> assertEquals("authorName", book.getAuthorName()),
                () -> assertEquals("genreName", book.getGenre())
        );


    }
}