package ru.zdoher.library.service;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorService {

    int count();

    List<Author> getAll();

    Author getById(Integer id);

    boolean deleteById(Integer id);

    void insert(Author author);

    void update(Author author);

    boolean isExist(Integer id);
}
