package ru.zdoher.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zdoher.library.repositories.AuthorRepository;
import ru.zdoher.library.domain.Author;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Class AuthorService")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    private HashMap<Long, Author> authorHashMap;


    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorRepository);

        authorHashMap = new HashMap<>();
        authorHashMap.put(1L, new Author("author1"));
        authorHashMap.put(2L, new Author("author2"));

    }

    @Test
    @DisplayName(" get by id correct")
    void authorGetById() {
        when(authorRepository.getById(1L)).thenReturn(authorHashMap.get(1L));

        assertAll(
                () -> assertEquals("author1", authorService.getById(1L).getName())
        );
    }

    @Test
    @DisplayName(" get by null correct")
    void authorGetNull() {
        when(authorRepository.getById(3L)).thenReturn(null);

        assertAll(
                () -> assertNull(authorService.getById(3L))
        );

    }

}