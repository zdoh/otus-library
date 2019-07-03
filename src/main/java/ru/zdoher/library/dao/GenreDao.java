package ru.zdoher.library.dao;

import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    List<Genre> getAll();

    Genre getById(int id);

    void deleteById(int id);

    void insert(Genre genre);

    void update(Genre genre);
}
