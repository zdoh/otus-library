package ru.zdoher.library.controller;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Genre;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.GenreService;
import ru.zdoher.library.service.MessageService;

@Service
public class GenreControllerImpl implements GenreController {
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
        consoleService.printString(messageService.getMessage("genre.newName"));
        String newGenreName = consoleService.getString();
        genreService.insert(new Genre(null, newGenreName));
        consoleService.printString(messageService.getMessage("genre.newSuccess"));
    }

    @Override
    public void showAll() {
        genreService.getAll().forEach(g -> consoleService.printString(g.toString()));
    }

    @Override
    public void deleteById(String id) {
        String decision;

        do {
            consoleService.printString(messageService.getMessage("genre.delAttention"));
            decision = consoleService.getString();
            if ("no".equals(decision)) return;
        } while (!"yes".equals(decision));

        Long longId = correctId(id);
        if (longId == null) return;

        if (genreService.deleteById(longId)) {
            consoleService.printString(messageService.getMessage("genre.delSuccess"));
        } else {
            consoleService.printString(messageService.getMessage("genre.wrongId"));
        }

    }

    @Override
    public void update(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        if (genreService.isExist(longId)) {
            consoleService.printString(messageService.getMessage("genre.newName"));
            String newName = consoleService.getString();
            genreService.update(new Genre(longId, newName));
            consoleService.printString(messageService.getMessage("genre.changeSuccess"));
        } else {
            consoleService.printString(messageService.getMessage("genre.wrongId"));
        }

    }

    private Long correctId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            consoleService.printString(messageService.getMessage("genre.wrongIdName"));
        }

        return null;
    }
}
