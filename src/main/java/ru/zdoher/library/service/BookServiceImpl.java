package ru.zdoher.library.service;

import ru.zdoher.library.dao.BookDao;
import ru.zdoher.library.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAllByAuthorId(int bookId) {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void insert(Book book) {

    }
}
