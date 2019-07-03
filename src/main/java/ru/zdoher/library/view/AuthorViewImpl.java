package ru.zdoher.library.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.AuthorService;
import ru.zdoher.library.service.ConsoleService;

import java.util.List;

@Service
public class AuthorViewImpl implements AuthorView {
    private AuthorService authorService;
    private ConsoleService consoleService;

    public AuthorViewImpl(AuthorService authorService, ConsoleService consoleService) {
        this.authorService = authorService;
        this.consoleService = consoleService;
    }

    @Override
    public void addAuthor() {
        consoleService.printString("Хотите добавить нового автора? Введите нового автора:");
        String newAuthorName = consoleService.getString();
        authorService.insert(new Author(null, newAuthorName));
        consoleService.printString("Новый автор успешно добавлен");
    }


    @Override
    public void showAll() {
        authorService.getAll().forEach( a -> consoleService.printString(a.toString()));
    }


    @Override
    public void deleteById(String id) {
        String decision;

        do {
            consoleService.printString("Если вы удалите автора, то будут удалены все его книги. Вы уверены, что хотите удалить (yes/no):");
            decision = consoleService.getString();
            if ("no".equals(decision)) return;
        } while (!"yes".equals(decision));

        try {
            if (authorService.deleteById(Integer.parseInt(id))) {
                consoleService.printString("Автор успешно удален");
            } else {
                consoleService.printString("Автора с таким id нет");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильный id для удаления");
        }
    }

    @Override
    public void update(String id) {
        try {
            Integer integerId = Integer.parseInt(id);

            if (authorService.isExist(integerId)) {
                consoleService.printString("Укажите новое имя автора:");
                String newName = consoleService.getString();
                authorService.update(new Author(integerId, newName));
                consoleService.printString("Новое имя установлено.");
            }
        } catch (NumberFormatException e) {
            consoleService.printString("Неправильный id для удаления");
        }
    }

}
