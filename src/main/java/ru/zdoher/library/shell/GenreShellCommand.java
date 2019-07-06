package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.controller.GenreController;

@ShellComponent
public class GenreShellCommand {

    private GenreController genreController;

    public GenreShellCommand(GenreController genreController) {
        this.genreController = genreController;
    }

    @ShellMethod("show all genre")
    public void genreShowAll() {
        genreController.showAll();
    }

    @ShellMethod("add genre")
    public void genreAdd() {
        genreController.addGenre();
    }

    @ShellMethod("Delete genre by id. Use: genre-delete id")
    public void genreDelete(@ShellOption String id) {
        genreController.deleteById(id);
    }

    @ShellMethod("Rename genre by id. Use: genre-rename id")
    public void genreRename(@ShellOption String id) {
        genreController.update(id);
    }
}
