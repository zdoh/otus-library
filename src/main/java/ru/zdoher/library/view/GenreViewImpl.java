package ru.zdoher.library.view;

import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Genre;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.GenreService;

import java.util.List;

@Service
public class GenreViewImpl implements GenreView {
    private GenreService genreService;
    private ConsoleService consoleService;

    public GenreViewImpl(GenreService genreService, ConsoleService consoleService) {
        this.genreService = genreService;
        this.consoleService = consoleService;
    }

    @Override
    public void addGenre() {
        consoleService.printString("Хотите добавить новый жанр? Введите название нового жанра:");
        String newGenreName = consoleService.getString();
        genreService.insert(new Genre(null, newGenreName));
        consoleService.printString("Новый жанр успешно добавлен");
    }

    @Override
    public void showAll() {
        genreService.getAll().forEach(g -> consoleService.printString(g.toString()));
    }

    @Override
    public void deleteById(String id) {
        String decision;

        do {
            consoleService.printString("Если вы удалите жанр, то будут удалены все книги с этим жанром. Вы уверены, что хотите удалить (yes/no):");
            decision = consoleService.getString();
            if ("no".equals(decision)) return;
        } while (!"yes".equals(decision));

        try {
            if (genreService.deleteById(Integer.parseInt(id))) {
                consoleService.printString("Жанр успешно удален");
            } else {
                consoleService.printString("Жанра с таким id нет");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильный id для удаления");
        }
    }

    @Override
    public void update(String id) {
        try {
            Integer integerId = Integer.parseInt(id);

            if (genreService.getById(integerId) != null) {
                consoleService.printString("Укажите новый жанр:");
                String newName = consoleService.getString();
                genreService.update(new Genre(integerId, newName));
                consoleService.printString("Новый жанр установлен.");
            }
        } catch (NumberFormatException e) {
            consoleService.printString("Неправильный id для удаления");
        }
    }
}
