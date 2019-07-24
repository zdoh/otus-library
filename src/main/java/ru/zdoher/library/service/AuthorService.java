package ru.zdoher.library.service;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(String id);

    boolean deleteById(String id);

    void insert(Author author);

    void update(Author author);

    boolean isExist(String id);
}
