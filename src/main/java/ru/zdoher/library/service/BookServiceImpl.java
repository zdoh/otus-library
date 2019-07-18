package ru.zdoher.library.service;

import org.springframework.data.domain.Sort;
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
        return bookRepository.findAll();
    }


    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if(isExist(id)) {
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }

    @Override
    public boolean isExist(Long id) {
        return bookRepository.existsById(id);
    }
}
