package ru.zdoher.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.exception.NotFoundException;
import ru.zdoher.library.repositories.AuthorRepository;
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

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/author-list")
    public String authorList(Model model) {
        List<Author> authorList = authorRepository.findAll();
        model.addAttribute("authors", authorList);
        return "author-list";
    }

    @GetMapping("/author-delete")
    public String authorDelete(@RequestParam("id") String id) {
        authorRepository.deleteById(id);
        return "redirect:/author-list";
    }


    @GetMapping("/author-edit")
    public String authorEdit(@RequestParam("id") String id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "author-edit";
    }

    @PostMapping("/author-edit")
    public String authorEditPost(Author author) {
        authorRepository.save(author);
        return "redirect:/author-list";
    }

    @GetMapping("/author-new")
    public String authorNew(Model model) {
        model.addAttribute("author", new Author());
        return "author-edit";
    }

    @PostMapping("/author-new")
    public String authorNewPost(Author author) {
        authorRepository.insert(author);
        return "redirect:/author-list";
    }

}
