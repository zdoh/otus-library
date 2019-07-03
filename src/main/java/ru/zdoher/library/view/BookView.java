package ru.zdoher.library.view;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookView {

    void addBook();

    void showAll();

    void getById(String id);

    void delete(String id);

}
