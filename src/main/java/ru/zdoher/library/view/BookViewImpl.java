package ru.zdoher.library.view;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.service.*;


@Service
public class BookViewImpl implements BookView {
    private BookService bookService;
    private ConsoleService consoleService;
    private AuthorService authorService;
    private GenreService genreService;
    private MessageService messageService;

    public BookViewImpl(BookService bookService, ConsoleService consoleService, AuthorService authorService, GenreService genreService, MessageService messageService) {
        this.bookService = bookService;
        this.consoleService = consoleService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.messageService = messageService;
    }

    @Override
    public void addBook() {
        Book newBook = new Book();
        consoleService.printString(messageService.getMessage("book.newName"));
        newBook.setName(consoleService.getString());

        String tempString;

        while(true) {
            authorService.getAll().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printString(messageService.getMessage("book.newAuthorId"));
            tempString = consoleService.getString();
            if ("END".equals(tempString)) return;

            Long longId = correctId(tempString);
            if (longId == null) continue;

            if (authorService.isExist(longId)) {
               newBook.setAuthor(authorService.getById(longId));
               break;
            } else {
                consoleService.printString(messageService.getMessage("book.wrongAuthorId"));
            }
        }

        while (true) {
            genreService.getAll().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printString(messageService.getMessage("book.newGenreId"));
            tempString = consoleService.getString();
            if ("END".equals(tempString)) return;

            Long longId = correctId(tempString);
            if (longId == null) continue;

            if (genreService.isExist(longId)) {
                newBook.setGenre(genreService.getById(longId));
                break;
            } else {
                consoleService.printString(messageService.getMessage("book.wrongGenreId"));
            }

        }

        if (bookService.insert(newBook)) {
            consoleService.printString(messageService.getMessage("book.newBookSuccess"));
        } else {
            consoleService.printString(messageService.getMessage("book.newBookError"));
        }
    }

    @Override
    public void showAll() {
        bookService.getAll().forEach(b -> consoleService.printString(b.toString()));
    }

    @Override
    public void getById(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        Book tmpBook = bookService.getById(longId);

        if (tmpBook == null) {
            consoleService.printString(messageService.getMessage("book.wrongId"));
        } else {
            consoleService.printString(tmpBook.toString());
        }
    }

    @Override
    public void delete(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        if (bookService.deleteById(longId)) {
            consoleService.printString(messageService.getMessage("book.deleteSucces"));
        } else {
            consoleService.printString(messageService.getMessage("book.wrongId"));
        }
    }

    private Long correctId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            consoleService.printString(messageService.getMessage("book.enterNumber"));
        }

        return null;
    }
}
