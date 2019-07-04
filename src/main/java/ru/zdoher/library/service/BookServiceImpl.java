package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.dao.BookDao;
import ru.zdoher.library.domain.Book;

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
    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if(isExist(id)) {
            return false;
        }

        bookDao.deleteById(id);
        return true;
    }

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
