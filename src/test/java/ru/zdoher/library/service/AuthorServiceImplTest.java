package ru.zdoher.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zdoher.library.dao.AuthorDao;
import ru.zdoher.library.domain.Author;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Class AuthorService")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorService authorService;

    private HashMap<Integer, Author> authorHashMap;


    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorDao);

        authorHashMap = new HashMap<>();
        authorHashMap.put(1, new Author(1L, "author1"));
        authorHashMap.put(2, new Author(2L, "author2"));

    }

    @Test
    @DisplayName(" count correct")
    void authorGetAllCount() {

        when(authorDao.count()).thenReturn(authorHashMap.size());

        assertAll(
                () -> assertEquals(2, authorService.count())
        );
    }

    @Test
    @DisplayName(" get by id correct")
    void authorGetById() {
        when(authorDao.getById(1L)).thenReturn(authorHashMap.get(1));

        assertAll(
                () -> assertEquals(Long.valueOf(1), authorService.getById(1L).getId()),
                () -> assertEquals("author1", authorService.getById(1L).getName())
        );
    }

    @Test
    @DisplayName(" get by null correct")
    void authorGetNull() {
        when(authorDao.getById(3L)).thenReturn(null);

        assertAll(
                () -> assertNull(authorService.getById(3L))
        );

    }

}