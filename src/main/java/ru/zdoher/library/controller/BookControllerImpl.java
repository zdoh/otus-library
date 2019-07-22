package ru.zdoher.library.controller;

import lombok.val;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.service.*;


@Service
public class BookControllerImpl implements BookController {
    private static final String NEW_NAME = "book.newName";
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
    private static final String COMMENT_DEL_WRONG_ID = "comment.delCommentWrongId";

    private ConsoleService consoleService;
    private MessageService messageService;
    private DBService dbService;

    public BookControllerImpl(ConsoleService consoleService, MessageService messageService, DBService dbService) {
        this.consoleService = consoleService;
        this.messageService = messageService;
        this.dbService = dbService;
    }

    @Override
    public void addBook() {
        Book newBook = new Book();
        consoleService.printServiceMessage(NEW_NAME);
        newBook.setName(consoleService.getString());

        String tempString;

        while(true) {
            dbService.getAllAuthor().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printServiceMessage(NEW_AUTHOR_ID);
            tempString = consoleService.getString();
            if (COMMAND_END.equals(tempString)) return;
            if (tempString == null) continue;

            val tempAuthor = dbService.getAuthorById(tempString);

            if (tempAuthor != null) {
               newBook.setAuthor(tempAuthor);
               break;
            } else {
                consoleService.printServiceMessage(WRONG_AUTHOR_ID);
            }
        }

        while (true) {
            dbService.getAllGenre().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printServiceMessage(NEW_GENRE_ID);
            tempString = consoleService.getString();
            if (COMMAND_END.equals(tempString)) return;
            if (tempString == null) continue;

            val tempGenre = dbService.getGenreById(tempString);

            if (tempGenre != null) {
                newBook.setGenre(tempGenre);
                break;
            } else {
                consoleService.printServiceMessage(WRONG_GENRE_ID);
            }

        }

        dbService.insertBook(newBook);
        consoleService.printServiceMessage(NEW_BOOK_SUCCESS);

    }

    @Override
    public void addCommentToBook(String id) {
        if (id == null) return;

        val tmpBook = dbService.getBookById(id);

        if (tmpBook == null) {
            consoleService.printServiceMessage(BOOK_WRONG_ID);
            return;
        }

        consoleService.printString(messageService.getMessage(COMMENT_NEW));
        val newComment = new Comment(consoleService.getString());

        tmpBook.getComments().add(newComment);
        dbService.updateBook(tmpBook);
        consoleService.printServiceMessage(COMMENT_NEW_SUCCESS);

    }

    @Override
    public void deleteCommentFromBook(String id) {
        if (id == null) return;

        Book tmpBook = dbService.getBookById(id);

        if (tmpBook == null) {
            consoleService.printServiceMessage(BOOK_WRONG_ID);
            return;
        }

        consoleService.printString(tmpBook.toString());

        for( int i = 1; i <= tmpBook.getComments().size(); i++) {
            consoleService.printString(i + " | " + tmpBook.getComments().get(i - 1));
        }

        consoleService.printServiceMessage(COMMENT_DEL);

        Integer commentId = correctId(consoleService.getString());
        if (commentId == null) return;

        if (commentId > 0 && commentId <= tmpBook.getComments().size()) {
            tmpBook.getComments().remove(commentId - 1);
            dbService.updateBook(tmpBook);
            consoleService.printServiceMessage(COMMENT_DEL_SUCCESS);
        } else {
            consoleService.printServiceMessage(COMMENT_DEL_WRONG_ID);
        }

    }

    @Override
    public void showAll() {
        dbService.getAllBook().forEach(b -> consoleService.printString(b.toString()));
    }

    @Override
    public void getById(String id) {

        if (id == null) return;

        Book tmpBook = dbService.getBookById(id);

        if (tmpBook != null) {
            consoleService.printString(tmpBook.toString());
            //dbService.getAllCommentForBook(tmpBook).forEach(c -> consoleService.printString(c.toString()));
        } else {
            consoleService.printServiceMessage(BOOK_WRONG_ID);
        }
    }

    @Override
    public void delete(String id) {
        if (id == null) return;

        if (dbService.deleteBookById(id)) {
            consoleService.printServiceMessage(DELETE_SUCCESS);
        } else {
            consoleService.printServiceMessage(BOOK_WRONG_ID);
        }
    }

    private Integer correctId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            consoleService.printServiceMessage(ENTER_NUMBER);
        }

        return null;
    }

}
