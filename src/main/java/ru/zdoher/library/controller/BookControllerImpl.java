package ru.zdoher.library.controller;

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
    private static final String WRONG_ID = "book.wrongId";
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

            Long longId = correctId(tempString);
            if (longId == null) continue;

            if (dbService.authorIsExist(longId)) {
               newBook.setAuthor(dbService.getAuthorById(longId));
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

            Long longId = correctId(tempString);
            if (longId == null) continue;

            if (dbService.genreIsExist(longId)) {
                newBook.setGenre(dbService.getGenreById(longId));
                break;
            } else {
                consoleService.printServiceMessage(WRONG_GENRE_ID);
            }

        }

        if (dbService.insertBook(newBook)) {
            consoleService.printServiceMessage(NEW_BOOK_SUCCESS);
        } else {
            consoleService.printServiceMessage(NEW_BOOK_ERROR);
        }
    }

    @Override
    public void addCommentToBook(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        Book tmpBook = dbService.getBookById(longId);

        consoleService.printString(messageService.getMessage(COMMENT_NEW) + " " + tmpBook.getName());
        String commentTmp = consoleService.getString();

        Comment comment = new Comment(commentTmp, tmpBook);

        if(dbService.insertComment(comment)) {
            consoleService.printServiceMessage(COMMENT_NEW_SUCCESS);
        } else {
            consoleService.printServiceMessage(COMMENT_NEW_ERROR);
        }

    }

    @Override
    public void deleteCommentFromBook(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        Book tmpBook = dbService.getBookById(longId);

        consoleService.printString(tmpBook.toString());
        tmpBook.getComments().forEach(c -> consoleService.printString(c.toString()));
        consoleService.printServiceMessage(COMMENT_DEL);

        Long commentId = correctId(consoleService.getString());
        if (commentId == null) return;


        if (dbService.commentInBookExist(tmpBook.getId(), commentId)) {

            dbService.deleteCommentById(commentId);
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
        Long longId = correctId(id);
        if (longId == null) return;

        Book tmpBook = dbService.getBookById(longId);

        if (tmpBook == null) {
            consoleService.printServiceMessage(WRONG_ID);
        } else {
            consoleService.printString(tmpBook.toString());
            tmpBook.getComments().forEach( c -> consoleService.printString(c.toString()));
        }
    }

    @Override
    public void delete(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        if (dbService.deleteBookById(longId)) {
            consoleService.printServiceMessage(DELETE_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }
    }

    private Long correctId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            consoleService.printServiceMessage(ENTER_NUMBER);
        }

        return null;
    }

}
