package ru.zdoher.library.service;

import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface DBService {

    List<Author> getAllAuthor();

    List<Genre> getAllGenre();

    List<Book> getAllBook();

    boolean authorIsExist(Long id);

    boolean genreIsExist(Long id);

    boolean commentInBookExist(Long bookId, Long commentId);

    Author getAuthorById(Long id);

    Genre getGenreById(Long id);

    Book getBookById(Long id);

    boolean insertBook(Book book);

    boolean insertComment(Comment comment);

    void insertAuthor(Author author);

    void insertGenre(Genre genre);

    void deleteCommentById(Long id);

    boolean deleteBookById(Long id);

    boolean deleteAuthorById(Long id);

    boolean deleteGenreById(Long id);

    void updateAuthor(Author author);

    void updateGenre(Genre genre);

}
