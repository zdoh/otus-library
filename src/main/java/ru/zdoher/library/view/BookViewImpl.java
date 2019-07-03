package ru.zdoher.library.view;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.service.AuthorService;
import ru.zdoher.library.service.BookService;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.GenreService;

import java.util.List;

@Service
public class BookViewImpl implements BookView {
    private BookService bookService;
    private ConsoleService consoleService;
    private AuthorService authorService;
    private GenreService genreService;

    public BookViewImpl(BookService bookService, ConsoleService consoleService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.consoleService = consoleService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public void addBook() {
        Book newBook = new Book();
        consoleService.printString("Укажите название книги");
        newBook.setName(consoleService.getString());

        String tempString;

        while(true) {
            authorService.getAll().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printString("Укажите id автора. Если хотите прекратить добавление наберите END");
            tempString = consoleService.getString();
            if ("END".equals(tempString)) return;

            Integer tempId = correctId(tempString);
            if (tempId == null) continue;

            if (authorService.isExist(tempId)) {
               newBook.setAuthorName(authorService.getById(tempId).getName());
               break;
            } else {
                consoleService.printString("Нет такого id автора");
            }
        }

        while (true) {
            genreService.getAll().forEach(s -> consoleService.printString(s.toString()));
            consoleService.printString("Укажите id жанра. Если хотите прекратить добавление наберите END");
            tempString = consoleService.getString();
            if ("END".equals(tempString)) return;

            Integer tempId = correctId(tempString);
            if (tempId == null) continue;

            if (genreService.isExist(tempId)) {
                newBook.setGenre(genreService.getById(tempId).getName());
                break;
            } else {
                consoleService.printString("Нет такого id жанра");
            }

        }

        bookService.insert(newBook);
        consoleService.printString("Книга успешно добавлена");
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
            consoleService.printString("Книги с указанным id нет");
        } else {
            consoleService.printString(tmpBook.toString());
        }
    }

    @Override
    public void delete(String id) {
        Integer tempId = correctId(id);
        if (tempId == null) return;

        if (bookService.deleteById(tempId)) {
            consoleService.printString("Книга удалена");
        } else {
            consoleService.printString("Книги с указанным id нет");
        }
    }

    private Integer correctId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            consoleService.printString("Укажите число");
        }

        return null;
    }
}
