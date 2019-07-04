package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.zdoher.library.view.GenreView;

@ShellComponent
public class GenreShellCommand {

    private GenreView genreView;

    public GenreShellCommand(GenreView genreView) {
        this.genreView = genreView;
    }

    @ShellMethod("show all genre")
    public void genreShowAll() {
        genreView.showAll();
    }

    @ShellMethod("add genre")
    public void genreAdd() {
        genreView.addGenre();
    }

    @ShellMethod("Delete genre by id. Use: genre-delete id")
    public void genreDelete(@ShellOption String id) {
        genreView.deleteById(id);
    }

    @ShellMethod("Rename genre by id. Use: genre-rename id")
    public void genreRename(@ShellOption String id) {
        genreView.update(id);
    }
}
