package ru.zdoher.library.service;

import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface DBService {

    List<Author> getAllAuthor();

    List<Genre> getAllGenre();

    List<Book> getAllBook();

    boolean authorIsExist(String id);

    boolean genreIsExist(String id);

    Author getAuthorById(String id);

    Genre getGenreById(String id);

    Book getBookById(String id);

    void insertBook(Book book);

    void insertAuthor(Author author);

    void insertGenre(Genre genre);

    boolean deleteBookById(String id);

    boolean deleteAuthorById(String id);

    boolean deleteGenreById(String id);

    /*boolean deleteCommentById(String bookId, String commentId);*/

    void updateAuthor(Author author);

    void updateGenre(Genre genre);

    void updateBook(Book book);

    boolean bookIsExist(String id);

    boolean authorDontHaveBookById(String id);

    boolean genreDontHaveBookById(String id);

}
