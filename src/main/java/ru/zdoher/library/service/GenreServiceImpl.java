package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.dao.GenreDao;
import ru.zdoher.library.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {

        if(!isExist(id)) {
            return false;
        }

        genreDao.deleteById(id);
        return true;
    }

    @Override
    public void update(Genre genre) {
        genreDao.update(genre);
    }

    @Override
    public void insert(Genre genre) {
        genreDao.insert(genre);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
