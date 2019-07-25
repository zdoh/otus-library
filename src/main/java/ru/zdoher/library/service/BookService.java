package ru.zdoher.library.service;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book getById(String id);

    boolean deleteById(String id);

    void insert(Book book);

    void update(Book book);

    boolean isExist(String id);

    boolean authorDontHaveBookById(String id);

    boolean genreDontHaveBookById(String id);
}
