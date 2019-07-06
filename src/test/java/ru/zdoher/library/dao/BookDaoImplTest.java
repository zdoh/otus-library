package ru.zdoher.library.dao;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@DisplayName("Класс BookDao")
@JdbcTest
@Import({BookDaoImpl.class})
class BookDaoImplTest {
    private final Long FIRST_ID = 1L;
    private final String FIRST_AUTHOR_NAME = "Анджей Сапковский";
    private final String FIRST_GENRE_NAME = "фэнтези";
    private final String FIRST_BOOK_NAME = "Последнее желание";
    private final Long NEW_AUTHOR_NAME = 6L;
    private final String NEW_BOOK_NAME = "book10";
    private final int NEW_SIZE = 4;

    @Autowired
    private BookDao bookDao;

    @DisplayName(" проверка получения всего корретно")
    @Test
    void bookDaoAll() {
        List<Book> bookList = bookDao.getAll();

        assertThat(bookList).isNotNull()
                .allMatch( b -> !b.getName().equals(""))
                .allMatch( b -> b.getId() != null && b.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 корректа")
    @Test
    void bookGetById() {
        Book book = bookDao.getById(FIRST_ID);

        assertThat(book).isNotNull()
                .matches( b -> b.getId().equals(FIRST_ID))
                .matches( b -> b.getAuthor().getName().equals(FIRST_AUTHOR_NAME))
                .matches( b -> b.getGenre().getName().equals(FIRST_GENRE_NAME))
                .matches( b -> b.getName().equals(FIRST_BOOK_NAME));
    }

    @DisplayName(" проверка вставки новой книги корректна")
    @Test
    void bookAdd() {
        bookDao.insert(new Book(null, NEW_BOOK_NAME,
                new Author(FIRST_ID, FIRST_AUTHOR_NAME),
                new Genre(FIRST_ID, FIRST_GENRE_NAME)));

        Book book = bookDao.getById(NEW_AUTHOR_NAME);

        assertThat(book)
                .matches( b -> b.getId().equals(NEW_AUTHOR_NAME))
                .matches( b -> b.getName().equals(NEW_BOOK_NAME))
                .matches( b -> b.getAuthor().getName().equals(FIRST_AUTHOR_NAME))
                .matches( b -> b.getGenre().getName().equals(FIRST_GENRE_NAME));
    }

    @DisplayName(" проверка удаления книги корректна")
    @Test
    void bookDelete() {
        bookDao.deleteById(FIRST_ID);

        List<Book> authorList = bookDao.getAll();

        assertThat(authorList.size()).isEqualTo(NEW_SIZE);

    }

}