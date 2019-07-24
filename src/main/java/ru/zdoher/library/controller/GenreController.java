package ru.zdoher.library.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zdoher.library.domain.Genre;
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
    public String getGenreList(Model model) {
        List<Genre> genreList = dbService.getAllGenre();
        model.addAttribute("genres", genreList);
        return "genre-list";
    }


/*
    public void addGenre() {
        consoleService.printServiceMessage(NEW_NAME);
        String newGenreName = consoleService.getString();
        dbService.insertGenre(new Genre(newGenreName));
        consoleService.printServiceMessage(NEW_SUCCESS);
    }

    public void showAll() {
        dbService.getAllGenre().forEach(g -> consoleService.printString(g.toString()));
    }

    public void deleteById(String id) {
        String decision;

        do {
            String fullDelAttentionMes = messageService.getMessage(DEL_ATTENTION) + " ("
                    + messageService.getMessage(DELETE_YES) + "/"
                    + messageService.getMessage(DELETE_NO) + "):";
            consoleService.printString(fullDelAttentionMes);
            decision = consoleService.getString();
            if (DELETE_NO.equals(decision)) return;
        } while (!DELETE_YES.equals(decision));

        if (dbService.deleteGenreById(id)) {
            consoleService.printServiceMessage(DEL_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }
    }

    public void update(String id) {
        if (id == null) return;

        val tmpGenre = dbService.getGenreById(id);

        if (tmpGenre != null) {
            consoleService.printServiceMessage(NEW_NAME);
            tmpGenre.setName(consoleService.getString());
            dbService.updateGenre(tmpGenre);
            consoleService.printServiceMessage(CHANGE_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }
    }*/
}
