package ru.zdoher.library.dao;

import org.springframework.stereotype.Service;
import ru.zdoher.library.model.Book;

import java.util.List;

@Service
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public void add(Book book) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public List<Book> getAllByAuthorId(int bookId) {
        return null;
    }
}
