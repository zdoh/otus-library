package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.view.AuthorView;

@ShellComponent
public class AuthorShellCommand {
    private AuthorView authorView;

    public AuthorShellCommand(AuthorView authorView) {
        this.authorView = authorView;
    }

    /*@ShellMethod("Command to count")
    public void count() {
        System.out.println(authorView.count());
    }*/

    @ShellMethod("Command to show all")
    public void authorShowAll() {
        authorView.showAll();
    }

    @ShellMethod("Add author")
    public void authorAdd() {
        authorView.addAuthor();
    }

    @ShellMethod("Delete author by id. Use: author-delete id")
    public void authorDelete(@ShellOption String id) {
        authorView.deleteById(id);
    }

    @ShellMethod("Rename author by id. Use: author-rename id")
    public void authorRename(@ShellOption String id) {
        authorView.update(id);
    }
}
