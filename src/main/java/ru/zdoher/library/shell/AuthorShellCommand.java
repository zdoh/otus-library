package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.controller.AuthorController;

@ShellComponent
public class AuthorShellCommand {
    private AuthorController authorController;

    public AuthorShellCommand(AuthorController authorController) {
        this.authorController = authorController;
    }

    /*@ShellMethod("Command to count")
    public void count() {
        System.out.println(authorController.count());
    }*/

    @ShellMethod("Command to show all")
    public void authorShowAll() {
        authorController.showAll();
    }

    @ShellMethod("Add author")
    public void authorAdd() {
        authorController.addAuthor();
    }

    @ShellMethod("Delete author by id. Use: author-delete №")
    public void authorDelete(@ShellOption String id) {
        authorController.deleteById(id);
    }

    @ShellMethod("Rename author by id. Use: author-rename №")
    public void authorRename(@ShellOption String id) {
        authorController.update(id);
    }
}
