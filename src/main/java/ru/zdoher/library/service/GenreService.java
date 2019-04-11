package ru.zdoher.library.service;

import ru.zdoher.library.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getById(int id);

    void deleteById(int id);

    void insert(Genre genre);
}
