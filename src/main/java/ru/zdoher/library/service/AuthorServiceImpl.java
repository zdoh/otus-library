package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.dao.AuthorDao;
import ru.zdoher.library.dao.AuthorDaoImpl;
import ru.zdoher.library.model.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getById(int id) {
        return authorDao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        authorDao.getById(id);
    }

    @Override
    public void insert(Author author) {
        authorDao.insert(author);
    }
}
