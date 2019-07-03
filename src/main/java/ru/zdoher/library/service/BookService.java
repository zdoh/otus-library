package ru.zdoher.library.service;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getAllByAuthorId(int authorId);

    Book getById(int id);

    boolean deleteById(int id);

    void insert(Book book);

    boolean isExist(int id);
}
