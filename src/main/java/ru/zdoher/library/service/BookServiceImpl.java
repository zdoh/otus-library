package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.repositories.BookRepository;
import ru.zdoher.library.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }


    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!isExist(id)) {
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean insert(Book book) {
        return bookRepository.insert(book);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
