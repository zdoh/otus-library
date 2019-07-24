package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.repositories.GenreRepository;
import ru.zdoher.library.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(String id) {
        if (isExist(id)) {
            genreRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void insert(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public boolean isExist(String id) {
        return genreRepository.existsById(id);
    }
}
