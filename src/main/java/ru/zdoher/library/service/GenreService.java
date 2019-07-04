package ru.zdoher.library.service;

import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getById(Long id);

    boolean deleteById(Long id);

    void insert(Genre genre);

    void update(Genre genre);

    boolean isExist(Long id);
}
