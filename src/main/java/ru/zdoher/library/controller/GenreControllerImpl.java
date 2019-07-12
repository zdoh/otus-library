package ru.zdoher.library.controller;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Genre;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.GenreService;
import ru.zdoher.library.service.MessageService;

@Service
public class GenreControllerImpl implements GenreController {
    private static final String NEW_NAME = "genre.newName";
    private static final String NEW_SUCCESS = "genre.newSuccess";
    private static final String DEL_ATTENTION = "genre.delAttention";
    private static final String DELETE_YES = "yes";
    private static final String DELETE_NO = "no";
    private static final String DEL_SUCCESS = "genre.delSuccess";
    private static final String WRONG_ID = "genre.wrongId";
    private static final String CHANGE_SUCCESS = "genre.changeSuccess";
    private static final String WRONG_ID_NAME = "genre.wrongIdName";

    private GenreService genreService;
    private ConsoleService consoleService;
    private MessageService messageService;

    public GenreControllerImpl(GenreService genreService, ConsoleService consoleService, MessageService messageService) {
        this.genreService = genreService;
        this.consoleService = consoleService;
        this.messageService = messageService;
    }

    @Override
    public void addGenre() {
        consoleService.printServiceMessage(NEW_NAME);
        String newGenreName = consoleService.getString();
        genreService.insert(new Genre(newGenreName));
        consoleService.printServiceMessage(NEW_SUCCESS);
    }

    @Override
    public void showAll() {
        genreService.getAll().forEach(g -> consoleService.printString(g.toString()));
    }

    @Override
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

        Long longId = correctId(id);
        if (longId == null) return;

        if (genreService.deleteById(longId)) {
            consoleService.printServiceMessage(DEL_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }

    }

    @Override
    public void update(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        Genre tmpGenre = genreService.getById(longId);

        if (tmpGenre != null) {
            consoleService.printServiceMessage(NEW_NAME);
            tmpGenre.setName(consoleService.getString());
            genreService.update(tmpGenre);
            consoleService.printServiceMessage(CHANGE_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }

    }

    private Long correctId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            consoleService.printServiceMessage(WRONG_ID_NAME);
        }

        return null;
    }
}
