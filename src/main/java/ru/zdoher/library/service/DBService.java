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

    /*List<Comment> getAllCommentForBook(Book book);*/

    boolean authorIsExist(String id);

    boolean genreIsExist(String id);

    boolean commentInBookExist(String bookId, String commentId);

    Author getAuthorById(String id);

    Genre getGenreById(String id);

    Book getBookById(String id);

    void insertBook(Book book);

    void insertComment(Comment comment);

    void insertAuthor(Author author);

    void insertGenre(Genre genre);

    void deleteCommentById(String id);

    boolean deleteBookById(String id);

    boolean deleteAuthorById(String id);

    boolean deleteGenreById(String id);

    boolean deleteCommentById(String bookId, String commentId);

    void updateAuthor(Author author);

    void updateGenre(Genre genre);

    void updateBook(Book book);

    boolean bookIsExist(String id);


}
