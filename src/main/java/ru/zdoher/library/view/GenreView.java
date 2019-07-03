package ru.zdoher.library.view;

import ru.zdoher.library.domain.Genre;

import java.util.List;

public interface GenreView {

    void addGenre();

    void showAll();

    void deleteById(String id);

    void update(String id);
}
