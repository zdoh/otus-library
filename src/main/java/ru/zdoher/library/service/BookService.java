package ru.zdoher.library.service;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book getById(Long id);

    boolean deleteById(Long id);

    void insert(Book book);

    boolean isExist(Long id);
}
