package ru.zdoher.library.dao;

import ru.zdoher.library.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();

    Author getById(int id);

    void add(Author author);

    void deleteById(int id);

    void insert(Author author);
}
