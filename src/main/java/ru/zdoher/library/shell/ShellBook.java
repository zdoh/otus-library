package ru.zdoher.library.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.dao.BookDao;
import ru.zdoher.library.model.Book;
import ru.zdoher.library.service.BookService;

@ShellComponent
public class ShellBook {
    private BookService bookService;

    public ShellBook(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Show all book")
    public void bookShowAll() {
        bookService.getAll().forEach(System.out::println);
    }

    @ShellMethod("Show book by id")
    public void bookShow(@ShellOption String id) {
        try {
            System.out.println(bookService.getById(Integer.parseInt(id)));
        } catch
         (NumberFormatException e) {
            System.out.println("Укажите номер книги для отображения");
        }

    }
}
