package ru.zdoher.library.repositories;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getAll();

    Author getById(Long id);

    boolean deleteById(Long id);

    void insert(Author author);

    void update(Author author);
}
