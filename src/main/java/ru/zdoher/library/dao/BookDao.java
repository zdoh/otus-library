package ru.zdoher.library.dao;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();

    List<Book> getAllByAuthorId(int bookId);

    Book getById(int id);

    void deleteById(int id);

    void insert(Book book);

}
