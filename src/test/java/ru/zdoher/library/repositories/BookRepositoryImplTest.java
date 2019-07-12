package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс BookRepository")
@DataJpaTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class, AuthorRepositoryImpl.class})
class BookRepositoryImplTest {
    private static final Long FIRST_ID = 1L;
    private static final String FIRST_AUTHOR_NAME = "Анджей Сапковский";
    private static final String FIRST_GENRE_NAME = "фэнтези";
    private static final String FIRST_BOOK_NAME = "Последнее желание";
    private static final Long NEW_AUTHOR_NAME = 6L;
    private static final String NEW_BOOK_NAME = "book10";
    private static final int NEW_SIZE = 4;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @DisplayName(" проверка получения всего корретно")
    @Test
    void bookDaoAll() {
        List<Book> bookList = bookRepository.getAll();

        assertThat(bookList).isNotNull()
                .allMatch( b -> !b.getName().equals(""))
                .allMatch( b -> b.getId() != null && b.getId() > 0);
    }

    @DisplayName(" проверка получения id 1 корректа")
    @Test
    void bookGetById() {
        Book book = bookRepository.getById(FIRST_ID);

        assertThat(book).isNotNull()
                .matches( b -> b.getId().equals(FIRST_ID))
                .matches( b -> b.getAuthor().getName().equals(FIRST_AUTHOR_NAME))
                .matches( b -> b.getGenre().getName().equals(FIRST_GENRE_NAME))
                .matches( b -> b.getName().equals(FIRST_BOOK_NAME));
    }

    @DisplayName(" проверка вставки новой книги корректна")
    @Test
    void bookAdd() {
        bookRepository.insert(new Book(NEW_BOOK_NAME,
                authorRepository.getById(FIRST_ID),
                genreRepository.getById(FIRST_ID)));

        Book book = bookRepository.getById(NEW_AUTHOR_NAME);

        assertThat(book)
                .matches( b -> b.getId().equals(NEW_AUTHOR_NAME))
                .matches( b -> b.getName().equals(NEW_BOOK_NAME))
                .matches( b -> b.getAuthor().getName().equals(FIRST_AUTHOR_NAME))
                .matches( b -> b.getGenre().getName().equals(FIRST_GENRE_NAME));
    }

    @DisplayName(" проверка удаления книги корректна")
    @Test
    void bookDelete() {
        bookRepository.deleteById(FIRST_ID);

        List<Book> authorList = bookRepository.getAll();

        assertThat(authorList.size()).isEqualTo(NEW_SIZE);

    }

}