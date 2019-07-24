package ru.zdoher.library.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zdoher.library.domain.Genre;
import ru.zdoher.library.exception.NotFoundException;
import ru.zdoher.library.repositories.BookRepository;
import ru.zdoher.library.repositories.GenreRepository;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.DBService;
import ru.zdoher.library.service.MessageService;

import java.util.List;

@Controller
public class GenreController {
/*    private static final String NEW_NAME = "genre.newName";
    private static final String NEW_SUCCESS = "genre.newSuccess";
    private static final String DEL_ATTENTION = "genre.delAttention";
    private static final String DELETE_YES = "yes";
    private static final String DELETE_NO = "no";
    private static final String DEL_SUCCESS = "genre.delSuccess";
    private static final String WRONG_ID = "genre.wrongId";
    private static final String CHANGE_SUCCESS = "genre.changeSuccess";
    private static final String WRONG_ID_NAME = "genre.wrongIdName";*/

/*    private ConsoleService consoleService;
    private MessageService messageService;*/
    private DBService dbService;

    public GenreController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/genre-list")
    public String genreList(Model model) {
        List<Genre> genreList = dbService.getAllGenre();
        model.addAttribute("genres", genreList);
        model.addAttribute("genre", new Genre());
        return "genre-list";
    }

    @GetMapping("/genre-delete")
    public String genreDelete(@RequestParam("id") String id) {
        if (dbService.genreDontHaveBookById(id)) {
            dbService.deleteGenreById(id);
        }

        // HELP!!! тут не знаю как отправить сообщение, чтобы в html упало о том что у автора есть книги
        // поэтому удалить нельзя.

        return "redirect:/genre-list";
    }


    @GetMapping("/genre-edit")
    public String genreEdit(@RequestParam("id") String id, Model model) {
        Genre genre = dbService.getGenreById(id);
        if (genre == null) throw new NotFoundException();
        model.addAttribute("genre", genre);
        return "genre-edit";
    }

    @PostMapping("/genre-edit")
    public String genreEditPost(Genre genre) {
        dbService.updateGenre(genre);
        return "redirect:/genre-list";
    }

    @PostMapping("/genre-new")
    public String genreNewPost(Genre genre) {
        dbService.insertGenre(genre);
        return "redirect:/genre-list";
    }
}
