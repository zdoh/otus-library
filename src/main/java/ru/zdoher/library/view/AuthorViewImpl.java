package ru.zdoher.library.view;


import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.AuthorService;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.MessageService;

@Service
public class AuthorViewImpl implements AuthorView {
    private AuthorService authorService;
    private ConsoleService consoleService;
    private MessageService messageService;

    public AuthorViewImpl(AuthorService authorService, ConsoleService consoleService, MessageService messageService) {
        this.authorService = authorService;
        this.consoleService = consoleService;
        this.messageService = messageService;
    }

    @Override
    public void addAuthor() {
        consoleService.printString(messageService.getMessage("author.newQuestion"));
        String newAuthorName = consoleService.getString();
        authorService.insert(new Author(null, newAuthorName));
        consoleService.printString(messageService.getMessage("author.newSuccess"));
    }


    @Override
    public void showAll() {
        authorService.getAll().forEach( a -> consoleService.printString(a.toString()));
    }


    @Override
    public void deleteById(String id) {
        String decision;

        do {
            consoleService.printString(messageService.getMessage("author.delAttention"));
            decision = consoleService.getString();
            if ("no".equals(decision)) return;
        } while (!"yes".equals(decision));

        Integer integerId = correctId(id);
        if (integerId == null) return;

        if (authorService.deleteById(integerId)) {
            consoleService.printString(messageService.getMessage("author.newSuccess"));
        } else {
            consoleService.printString(messageService.getMessage("author.wrongId"));
        }

    }

    @Override
    public void update(String id) {
        Integer integerId = correctId(id);
        if (integerId == null) return;

        if (authorService.isExist(integerId)) {
            consoleService.printString(messageService.getMessage("author.newName"));
            String newName = consoleService.getString();
            authorService.update(new Author(integerId, newName));
            consoleService.printString(messageService.getMessage("author.newNameSuccess"));
        } else {
            consoleService.printString(messageService.getMessage("author.wrongId"));
        }

    }

    private Integer correctId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            consoleService.printString(messageService.getMessage("author.wrongIdName"));
        }

        return null;
    }

}
