package ru.zdoher.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.exception.NotFoundException;
import ru.zdoher.library.repositories.AuthorRepository;
import ru.zdoher.library.repositories.BookRepository;
import ru.zdoher.library.service.DBService;

import java.util.List;


@Controller
public class AuthorController {
/*    private static final String NEW_QUESTION = "author.newQuestion";
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

 */

    private DBService dbService;

    public AuthorController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/author-list")
    public String authorList(Model model) {
        List<Author> authorList = dbService.getAllAuthor();
        model.addAttribute("authors", authorList);
        model.addAttribute("author", new Author());
        return "author-list";
    }

    @GetMapping("/author-delete")
    public String authorDelete(@RequestParam("id") String id) {
        if (dbService.authorDontHaveBookById(id)) {
            dbService.deleteAuthorById(id);
        }

        // HELP!!! тут не знаю как отправить сообщение, чтобы в html упало о том что у автора есть книги
        // поэтому удалить нельзя.

        return "redirect:/author-list";
    }


    @GetMapping("/author-edit")
    public String authorEdit(@RequestParam("id") String id, Model model) {
        Author author = dbService.getAuthorById(id);
        if (author == null) throw new NotFoundException();
        model.addAttribute("author", author);
        return "author-edit";
    }

    @PostMapping("/author-edit")
    public String authorEditPost(Author author) {
        dbService.updateAuthor(author);
        return "redirect:/author-list";
    }

    @PostMapping("/author-new")
    public String authorNewPost(Author author) {
        dbService.insertAuthor(author);
        return "redirect:/author-list";
    }

}
