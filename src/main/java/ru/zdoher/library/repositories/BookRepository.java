package ru.zdoher.library.repositories;

import ru.zdoher.library.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getById(Long id);

    boolean deleteById(Long id);

    boolean insert(Book book);

}
