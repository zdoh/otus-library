package ru.zdoher.library.service;

import ru.zdoher.library.domain.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> getAll();

    Book getById(String id);

    boolean deleteById(String id);

    void insert(Book book);

    void update(Book book);

    boolean isExist(String id);

    boolean commentIsExist(String bookId, String commentId);
}
