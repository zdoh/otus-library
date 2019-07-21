package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.domain.Genre;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private CommentService commentService;
    private ConsoleService consoleService;

    public DBServiceImpl(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService, ConsoleService consoleService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.consoleService = consoleService;
    }


    @Override
    public List<Author> getAllAuthor() {
        return authorService.getAll();
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreService.getAll();
    }

    @Override
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

/*    @Override
    public List<Comment> getAllCommentForBook(Book book) {
        return commentService.getAllForBook(book);
    }*/


    @Override
    public boolean authorIsExist(String id) {
        return authorService.isExist(id);
    }

    @Override
    public boolean genreIsExist(String id) {
        return genreService.isExist(id);
    }

    @Override
    public boolean commentInBookExist(String bookId, String commentId) {
        return bookService.commentIsExist(bookId, commentId);
    }

    @Override
    public Author getAuthorById(String id) {
        return authorService.getById(id);
    }

    @Override
    public Genre getGenreById(String id) {
        return genreService.getById(id);
    }

    @Override
    public Book getBookById(String id) {
        return bookService.getById(id);
    }

    @Override
    public void insertBook(Book book) {
        bookService.insert(book);
    }

    @Override
    public void insertComment(Comment comment) {
        commentService.insert(comment);
    }

    @Override
    public void insertAuthor(Author author) {
        authorService.insert(author);
    }

    @Override
    public void insertGenre(Genre genre) {
        genreService.insert(genre);
    }

    @Override
    public void deleteCommentById(String id) {
        commentService.deleteById(id);
    }

    @Override
    public boolean deleteBookById(String id) {
        return bookService.deleteById(id);
    }

    @Override
    public boolean deleteAuthorById(String id) {
        return authorService.deleteById(id);
    }

    @Override
    public boolean deleteGenreById(String id) {
        return genreService.deleteById(id);
    }

    @Override
    public boolean deleteCommentById(String bookId, String commentId) {
        return bookService.deleteCommentById(bookId, commentId);
    }

    @Override
    public void updateAuthor(Author author) {
        authorService.update(author);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreService.update(genre);
    }

    @Override
    public void updateBook(Book book) {
        bookService.update(book);
    }

    @Override
    public boolean bookIsExist(String id) {
        return bookService.isExist(id);
    }
}
