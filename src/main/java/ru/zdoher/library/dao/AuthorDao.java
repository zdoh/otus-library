package ru.zdoher.library.dao;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    List<Author> getAll();

    Author getById(Long id);

    void deleteById(Long id);

    void insert(Author author);

    void update(Author author);
}
