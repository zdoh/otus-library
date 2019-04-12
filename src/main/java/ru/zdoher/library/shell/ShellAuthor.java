package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.zdoher.library.service.AuthorService;

@ShellComponent
public class ShellAuthor {
    private AuthorService authorService;

    public ShellAuthor(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Command to count")
    public void count() {
        System.out.println(authorService.count());
    }

    @ShellMethod("Command to show all")
    public void authorAllShow() {
        authorService.getAll().forEach(System.out::println);
    }
}
