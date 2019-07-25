package ru.zdoher.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.zdoher.library.domain.Genre;
import ru.zdoher.library.exception.NotFoundException;
import ru.zdoher.library.service.DBService;

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
    public String listView(Model model, boolean showDelMess) {
        List<Genre> genreList = dbService.getAllGenre();
        model.addAttribute("genres", genreList);
        model.addAttribute("genre", new Genre());
        model.addAttribute("showDelMess", showDelMess);
        return "genre-list";
    }

    @PostMapping("/genre-delete")
    public String delete(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
        if (dbService.genreDontHaveBookById(id)) {
            dbService.deleteGenreById(id);
        } else {
            redirectAttributes.addAttribute("showDelMess", true);
        }

        return "redirect:/genre-list";
    }


    @GetMapping("/genre-edit")
    public String editView(@RequestParam("id") String id, Model model) {
        Genre genre = dbService.getGenreById(id);
        if (genre == null) throw new NotFoundException();
        model.addAttribute("genre", genre);
        return "genre-edit";
    }

    @PostMapping("/genre-edit")
    public String edit(Genre genre) {
        dbService.updateGenre(genre);
        return "redirect:/genre-list";
    }

    @PostMapping("/genre-new")
    public String add(Genre genre) {
        dbService.insertGenre(genre);
        return "redirect:/genre-list";
    }
}
