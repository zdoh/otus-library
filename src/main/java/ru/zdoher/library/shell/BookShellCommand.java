package ru.zdoher.library.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.view.BookView;

@ShellComponent
public class BookShellCommand {
    private BookView bookView;

    public BookShellCommand(BookView bookView) {
        this.bookView = bookView;
    }

    @ShellMethod("Show all book")
    public void bookShowAll() {
        bookView.showAll();
    }

    @ShellMethod("Show book by id. Use: book-show id")
    public void bookShow(@ShellOption String id) {
        bookView.getById(id);
    }

    @ShellMethod("Delete book by id. Use: book-delete id")
    public void bookDelete(@ShellOption String id) {
        bookView.delete(id);
    }

    @ShellMethod("Add book.")
    public void bookAdd() {
        bookView.addBook();
    }
}
