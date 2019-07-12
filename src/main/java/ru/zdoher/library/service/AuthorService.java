package ru.zdoher.library.service;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Long id);

    boolean deleteById(Long id);

    void insert(Author author);

    void update(Author author);

    boolean isExist(Long id);
}
