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
        Book book = bookDao.getById(1L);

        assertThat(book).isNotNull()
                .matches( b -> b.getId() == 1L)
                .matches( b -> b.getAuthor().getName().equals("Анджей Сапковский"))
                .matches( b -> b.getGenre().getName().equals("фэнтези"))
                .matches( b -> b.getName().equals("Последнее желание"));
    }

    @DisplayName(" проверка вставки новой книги корректна")
    @Test
    void bookAdd() {
        bookDao.insert(new Book(null, "book10", new Author(1L, "Анджей Сапковский"), new Genre(1L, "фэнтези")));

        Book book = bookDao.getById(6L);

        assertThat(book)
                .matches( b -> b.getId() == 6)
                .matches( b -> b.getName().equals("book10"))
                .matches( b -> b.getAuthor().getName().equals("Анджей Сапковский"))
                .matches( b -> b.getGenre().getName().equals("фэнтези"));
    }

    @DisplayName(" проверка удаления книги корректна")
    @Test
    void bookDelete() {
        bookDao.deleteById(5L);

        List<Book> authorList = bookDao.getAll();

        assertThat(authorList.size()).isEqualTo(4);

    }

}