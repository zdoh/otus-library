package ru.zdoher.library.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.DBService;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Class AuthorController")
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    private static final String AUTHOR1 = "author1";
    private static final String AUTHOR2 = "author2";
    private static final String AUTHOR3 = "author3";
    private static final String AUTHOR1_ID = "1";
    private static final String AUTHOR2_ID = "2";
    private static final String AUTHOR3_ID = "3";


    @Autowired
    private MockMvc mvc;

    @MockBean
    private DBService dbService;


    @DisplayName(" display all - correct")
    @Test
    void authorList() throws Exception {
        when(dbService.getAllAuthor()).thenReturn(Arrays.asList(new Author(AUTHOR1), new Author(AUTHOR2)));
        mvc.perform(get("/author-list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(AUTHOR1)))
                .andExpect(content().string(containsString(AUTHOR2)));
    }

    @DisplayName(" get author by id - correct")
    @Test
    void authorGetEditById() throws Exception {
        when(dbService.getAuthorById(AUTHOR1_ID)).thenReturn(new Author(AUTHOR1_ID, AUTHOR1));

        mvc.perform(get("/author-edit").param("id", AUTHOR1_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(AUTHOR1)));
    }

    @DisplayName(" display all - correct")
    @Test
    void authorNewPost() throws Exception {
        when(dbService.authorDontHaveBookById(AUTHOR1_ID)).thenReturn(true);
        mvc.perform(post("/author-delete")
                .param("id", AUTHOR1_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/author-list"));
        verify(dbService, times(1)).deleteAuthorById(AUTHOR1_ID);
    }
}