package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.dao.AuthorDao;
import ru.zdoher.library.domain.Author;

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
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!isExist(id)) {
            return false;
        }

        authorDao.deleteById(id);
        return true;
    }

    @Override
    public void insert(Author author) {
        authorDao.insert(author);
    }

    @Override
    public void update(Author author) {
        authorDao.update(author);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
