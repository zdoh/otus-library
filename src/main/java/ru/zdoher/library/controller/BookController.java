package ru.zdoher.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.exception.NotFoundException;
import ru.zdoher.library.service.*;

import java.util.List;


@Controller
// поставил аннотацию, тк у меня при редактирование book не сохранялись данные comments
@SessionAttributes("book")
public class BookController {
/*    private static final String NEW_NAME = "book.newName";
    private static final String NEW_AUTHOR_ID = "book.newAuthorId";
    private static final String WRONG_AUTHOR_ID = "book.wrongAuthorId";
    private static final String NEW_GENRE_ID = "book.newGenreId";
    private static final String WRONG_GENRE_ID = "book.wrongGenreId";
    private static final String NEW_BOOK_SUCCESS = "book.newBookSuccess";
    private static final String NEW_BOOK_ERROR = "book.newBookError";
    private static final String BOOK_WRONG_ID = "book.wrongId";
    private static final String DELETE_SUCCESS = "book.deleteSuccess";
    private static final String ENTER_NUMBER = "book.enterNumber";
    private static final String COMMAND_END = "END";
    private static final String COMMENT_NEW = "comment.newComment";
    private static final String COMMENT_NEW_SUCCESS = "comment.newSuccess";
    private static final String COMMENT_NEW_ERROR = "comment.newCommentError";
    private static final String COMMENT_DEL = "comment.delComment";
    private static final String COMMENT_DEL_SUCCESS = "comment.delSuccess";
    private static final String COMMENT_DEL_WRONG_ID = "comment.delCommentWrongId";*/

    private DBService dbService;

    public BookController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/book-list")
    public String bookList(Model model) {
        List<Book> bookList = dbService.getAllBook();
        model.addAttribute("books", bookList);
        return "book-list";
    }

    @GetMapping("/book-edit")
    public String bookEdit(@RequestParam("id") String id, Model model) {
        Book book = dbService.getBookById(id);
        if (book == null) throw new NotFoundException();
        model.addAttribute("book", book);
        model.addAttribute("genres", dbService.getAllGenre());
        model.addAttribute("authors", dbService.getAllAuthor());
        return "book-edit";
    }

    @PostMapping("/book-edit")
    public String bookEditPost(Book book) {
        dbService.updateBook(book);
        return "redirect:/book-list";
    }

    @GetMapping("/book-new")
    public String bookNewGet(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", dbService.getAllGenre());
        model.addAttribute("authors", dbService.getAllAuthor());
        return "book-new";
    }

    @PostMapping("/book-new")
    public String bookNewPost(Book book) {
        dbService.insertBook(book);
        return "redirect:/book-list";
    }

    @GetMapping("/book-delete")
    public String bookDelete(@RequestParam("id") String id) {
        dbService.deleteBookById(id);
        return "redirect:/book-list";
    }

    @GetMapping("/comment-delete")
    public String bookCommentDelete(@RequestParam("book_id") String bookId,
                                    @RequestParam("comment_id") String commentId) {
        System.out.println("2");
        Book book = dbService.getBookById(bookId);
        if (book == null) throw new NotFoundException();
        book.getComments().removeIf( c -> c.getId().toString().equals(commentId));
        dbService.updateBook(book);
        return "redirect:/book-edit?id=" + bookId;
    }

    @PostMapping("/comment-new")
    public String bookCommenNew(@RequestParam("book_id") String bookId,
                                    @RequestParam("new_comment") String new_comment) {

        System.out.println(bookId + " " + new_comment);
        Book book = dbService.getBookById(bookId);
        if (book == null) throw new NotFoundException();
        book.getComments().add(new Comment(new_comment));
        dbService.updateBook(book);
        return "redirect:/book-edit?id=" + bookId;
    }

}
