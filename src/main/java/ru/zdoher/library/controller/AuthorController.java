package ru.zdoher.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.exception.NotFoundException;
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
    public String listView(Model model, boolean showDelMess) {
        List<Author> authorList = dbService.getAllAuthor();
        model.addAttribute("authors", authorList);
        model.addAttribute("author", new Author());
        model.addAttribute("showDelMess", showDelMess);
        return "author-list";
    }

    @PostMapping("/author-delete")
    public String delete(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
        if (dbService.authorDontHaveBookById(id)) {
            dbService.deleteAuthorById(id);
        } else {
            redirectAttributes.addAttribute("showDelMess", true);
        }

        return "redirect:/author-list";
    }


    @GetMapping("/author-edit")
    public String editView(@RequestParam("id") String id, Model model) {
        Author author = dbService.getAuthorById(id);
        if (author == null) throw new NotFoundException();
        model.addAttribute("author", author);
        return "author-edit";
    }

    @PostMapping("/author-edit")
    public String edit(Author author) {
        dbService.updateAuthor(author);
        return "redirect:/author-list";
    }

    @PostMapping("/author-new")
    public String add(Author author) {
        dbService.insertAuthor(author);
        return "redirect:/author-list";
    }

}
