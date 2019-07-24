package ru.zdoher.library.controller;


import lombok.val;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.ConsoleService;
import ru.zdoher.library.service.DBService;
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

    private ConsoleService consoleService;
    private MessageService messageService;
    private DBService dbService;

    public AuthorControllerImpl(ConsoleService consoleService, MessageService messageService, DBService dbService) {
        this.consoleService = consoleService;
        this.messageService = messageService;
        this.dbService = dbService;
    }

    @Override
    public void addAuthor() {
        consoleService.printServiceMessage(NEW_QUESTION);
        val newAuthorName = consoleService.getString();
        dbService.insertAuthor(new Author(newAuthorName));
        consoleService.printServiceMessage(NEW_SUCCESS);
    }


    @Override
    public void showAll() {
        dbService.getAllAuthor().forEach( a -> consoleService.printString(a.toString()));
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

        if (dbService.deleteAuthorById(id)) {
            consoleService.printServiceMessage(DEL_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }
    }

    @Override
    public void update(String id) {
        if (id == null) return;

        val tempAuthor = dbService.getAuthorById(id);

        if (tempAuthor != null) {
            consoleService.printServiceMessage(NEW_NAME);
            tempAuthor.setName(consoleService.getString());
            dbService.updateAuthor(tempAuthor);
            consoleService.printServiceMessage(NEW_NAME_SUCCESS);
        } else {
            consoleService.printServiceMessage(WRONG_ID);
        }
    }
}
