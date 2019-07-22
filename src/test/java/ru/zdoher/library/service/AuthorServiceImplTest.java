package ru.zdoher.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zdoher.library.repositories.AuthorRepository;
import ru.zdoher.library.domain.Author;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DisplayName("Class AuthorService")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {
    private static final String FIRST = "1";
    private static final String SECOND = "2";
    private static final String THIRD = "3";
    private static int authorCount;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    private HashMap<String, Author> authorHashMap;


    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorRepository);

        authorHashMap = new HashMap<>();
        authorHashMap.put(FIRST, new Author(FIRST, "author1"));
        authorHashMap.put(SECOND, new Author(SECOND,"author2"));

        authorCount = authorHashMap.size();
    }

    @Test
    @DisplayName(" get by id - correct")
    void authorGetById() {
        when(authorRepository.findById(FIRST)).thenReturn(java.util.Optional.ofNullable(authorHashMap.get(FIRST)));

        assertAll(
                () -> assertEquals("author1", authorService.getById("1").getName())
        );
    }

    @Test
    @DisplayName(" get by null - correct")
    void authorGetNull() {
        when(authorRepository.findById(THIRD)).thenReturn(Optional.empty());

        assertAll(
                () -> assertNull(authorService.getById(THIRD))
        );

    }

    @Test
    @DisplayName(" get all - correct ")
    void authorGetAll(){
        when(authorRepository.findAll()).thenReturn(new ArrayList<>(authorHashMap.values()));

        assertAll(
                () -> assertEquals(authorCount, authorService.getAll().size())
        );
    }

    @Test
    @DisplayName(" delete by id - correct")
    void authorDeleteById() {
        when(authorRepository.existsById(FIRST)).thenReturn(true);
        doNothing().when(authorRepository).deleteById(FIRST);

        authorService.deleteById(FIRST);

        verify(authorRepository,times(1)).deleteById(any(String.class));
    }

    @Test
    @DisplayName(" update by id - correct")
    void authorUpdateById() {
        when(authorRepository.save(any(Author.class))).thenReturn(null);

        authorService.update(new Author(FIRST));

        verify(authorRepository, times(1)).save(any(Author.class));
    }
}