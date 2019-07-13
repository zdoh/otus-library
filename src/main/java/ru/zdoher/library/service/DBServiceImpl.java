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


    @Override
    public boolean authorIsExist(Long id) {
        return authorService.isExist(id);
    }

    @Override
    public boolean genreIsExist(Long id) {
        return genreService.isExist(id);
    }

    @Override
    public boolean commentInBookExist(Long bookId, Long commentId) {
        return commentService.commentInBookExist(bookId, commentId);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorService.getById(id);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreService.getById(id);
    }

    @Override
    public Book getBookById(Long id) {
        return bookService.getById(id);
    }

    @Override
    public boolean insertBook(Book book) {
        return bookService.insert(book);
    }

    @Override
    public boolean insertComment(Comment comment) {
        return commentService.insert(comment);
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
    public void deleteCommentById(Long id) {
        commentService.deleteById(id);
    }

    @Override
    public boolean deleteBookById(Long id) {
        return bookService.deleteById(id);
    }

    @Override
    public boolean deleteAuthorById(Long id) {
        return authorService.deleteById(id);
    }

    @Override
    public boolean deleteGenreById(Long id) {
        return genreService.deleteById(id);
    }

    @Override
    public void updateAuthor(Author author) {
        authorService.update(author);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreService.update(genre);
    }
}
