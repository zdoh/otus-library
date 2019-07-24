package ru.zdoher.library.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.service.DBService;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Class AuthorController")
@WebMvcTest(AuthorController.class)
@RunWith(SpringRunner.class)
class AuthorControllerTest {
    private static final String AUTHOR1 = "author1";
    private static final String AUTHOR2 = "author2";
    private static final String AUTHOR1_ID = "1";
    private static final String AUTHOR2_ID = "2";


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
}