package ru.zdoher.library.view;

import ru.zdoher.library.domain.Author;

import java.util.List;

public interface AuthorView {

    void addAuthor();

    void showAll();

    void deleteById(String id);

    void update(String id);
}
