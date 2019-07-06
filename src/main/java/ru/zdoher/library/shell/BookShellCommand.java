package ru.zdoher.library.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.controller.BookController;

@ShellComponent
public class BookShellCommand {
    private BookController bookController;

    public BookShellCommand(BookController bookController) {
        this.bookController = bookController;
    }

    @ShellMethod("Show all book")
    public void bookShowAll() {
        bookController.showAll();
    }

    @ShellMethod("Show book by id. Use: book-show id")
    public void bookShow(@ShellOption String id) {
        bookController.getById(id);
    }

    @ShellMethod("Delete book by id. Use: book-delete id")
    public void bookDelete(@ShellOption String id) {
        bookController.delete(id);
    }

    @ShellMethod("Add book.")
    public void bookAdd() {
        bookController.addBook();
    }
}
