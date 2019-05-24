package ru.zdoher.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.zdoher.library.service.GenreService;

@ShellComponent
public class ShellGenre {
    private GenreService genreService;

    public ShellGenre(GenreService genreService) {
        this.genreService = genreService;
    }


    @ShellMethod("show all genre")
    public void genreShowAll() {
        genreService.getAll().forEach(System.out::println);
    }
}
