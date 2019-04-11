package ru.zdoher.library.service;

import ru.zdoher.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getAllByAuthorId(int bookId);

    Book getById(int id);

    void deleteById(int id);

    void insert(Book book);

}
