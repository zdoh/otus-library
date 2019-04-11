package ru.zdoher.library.service;

import ru.zdoher.library.model.Author;

import java.util.List;

public interface AuthorService {

    int count();

    List<Author> getAll();

    Author getById(int id);

    void deleteById(int id);

    void insert(Author author);
}
