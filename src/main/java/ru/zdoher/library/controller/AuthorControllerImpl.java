package ru.zdoher.library.controller;


import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.AuthorService;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.MessageService;

@Service
public class AuthorControllerImpl implements AuthorController {
    private static final String NEW_QUESTION = "author.newQuestion";
    private static final String NEW_SUCCESS = "author.newSuccess";
    private static final String DEL_ATTENTION = "author.delAttention";
    private static final String DEL_SUCCESS = "author.delSuccess";
    private static final String WRONG_ID = "author.wrongId";
    private static final String NEW_NAME = "author.newName";
    private static final String NEW_NAME_SUCCESS = "author.newNameSuccess";
    private static final String WRONG_ID_NAME = "author.wrongIdName";
    private static final String DELETE_YES = "yes";
    private static final String DELETE_NO = "no";

    private AuthorService authorService;
    private ConsoleService consoleService;
    private MessageService messageService;


    public AuthorControllerImpl(AuthorService authorService, ConsoleService consoleService, MessageService messageService) {
        this.authorService = authorService;
        this.consoleService = consoleService;
        this.messageService = messageService;
    }

    @Override
    public void addAuthor() {
        consoleService.printString(messageService.getMessage(NEW_QUESTION));
        String newAuthorName = consoleService.getString();
        authorService.insert(new Author(newAuthorName));
        consoleService.printString(messageService.getMessage(NEW_SUCCESS));
    }


    @Override
    public void showAll() {
        authorService.getAll().forEach( a -> consoleService.printString(a.toString()));
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

        if (authorService.deleteById(longId)) {
            consoleService.printString(messageService.getMessage(DEL_SUCCESS));
        } else {
            consoleService.printString(messageService.getMessage(WRONG_ID));
        }

    }

    @Override
    public void update(String id) {
        Long longId = correctId(id);
        if (longId == null) return;

        Author tempAuthor = authorService.getById(longId);

        if (tempAuthor != null) {
            consoleService.printString(messageService.getMessage(NEW_NAME));
            tempAuthor.setName(consoleService.getString());
            authorService.update(tempAuthor);
            consoleService.printString(messageService.getMessage(NEW_NAME_SUCCESS));
        } else {
            consoleService.printString(messageService.getMessage(WRONG_ID));
        }

    }

    private Long correctId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            consoleService.printString(messageService.getMessage(WRONG_ID_NAME));
        }

        return null;
    }

}
