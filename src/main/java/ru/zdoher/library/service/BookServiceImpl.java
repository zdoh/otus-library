package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.dao.BookDao;
import ru.zdoher.library.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getAllByAuthorId(int authorId) {
        return bookDao.getAllByAuthorId(authorId);
    }

    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }
}
