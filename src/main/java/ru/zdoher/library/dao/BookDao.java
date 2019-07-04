package ru.zdoher.library.dao;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();

    Book getById(Long id);

    void deleteById(Long id);

    void insert(Book book);

}
