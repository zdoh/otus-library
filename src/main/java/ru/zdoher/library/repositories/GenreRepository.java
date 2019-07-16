package ru.zdoher.library.repositories;

import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> getAll();

    Genre getById(Long id);

    boolean deleteById(Long id);

    void insert(Genre genre);

    void update(Genre genre);
}
