package ru.zdoher.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.zdoher.library.dao.AuthorDao;
import ru.zdoher.library.domain.Author;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class AuthorService")
class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;

    private AuthorService authorService;

    private HashMap<Integer, Author> authorHashMap;


    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorDao);

        authorHashMap = new HashMap<>();
        authorHashMap.put(1, new Author(1, "author1"));
        authorHashMap.put(2, new Author(2, "author2"));

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
        when(authorDao.getById(1)).thenReturn(authorHashMap.get(1));

        assertAll(
                () -> assertEquals(Integer.valueOf(1), authorService.getById(1).getId()),
                () -> assertEquals("author1", authorService.getById(1).getName())
        );
    }

    @Test
    @DisplayName(" get by null correct")
    void authorGetNull() {
        when(authorDao.getById(3)).thenReturn(null);

        assertAll(
                () -> assertNull(authorService.getById(3))
        );

    }

}