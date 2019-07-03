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

            Integer tempId = correctId(tempString);
            if (tempId == null) continue;

            if (authorService.isExist(tempId)) {
               newBook.setAuthorName(authorService.getById(tempId).getName());
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

            Integer tempId = correctId(tempString);
            if (tempId == null) continue;

            if (genreService.isExist(tempId)) {
                newBook.setGenre(genreService.getById(tempId).getName());
                break;
            } else {
                consoleService.printString(messageService.getMessage("book.wrongGenreId"));
            }

        }

        bookService.insert(newBook);
        consoleService.printString(messageService.getMessage("book.newBookSuccess"));
    }

    @Override
    public void showAll() {
        bookService.getAll().forEach(b -> consoleService.printString(b.toString()));
    }

    @Override
    public void getById(String id) {
        Integer tempId = correctId(id);
        if (tempId == null) return;

        Book tmpBook = bookService.getById(tempId);

        if (tmpBook == null) {
            consoleService.printString(messageService.getMessage("book.wrongId"));
        } else {
            consoleService.printString(tmpBook.toString());
        }
    }

    @Override
    public void delete(String id) {
        Integer tempId = correctId(id);
        if (tempId == null) return;

        if (bookService.deleteById(tempId)) {
            consoleService.printString(messageService.getMessage("book.deleteSucces"));
        } else {
            consoleService.printString(messageService.getMessage("book.wrongId"));
        }
    }

    private Integer correctId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            consoleService.printString(messageService.getMessage("book.enterNumber"));
        }

        return null;
    }
}
