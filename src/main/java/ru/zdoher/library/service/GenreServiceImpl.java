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
        return genreRepository.getAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return genreRepository.deleteById(id);
    }

    @Override
    public void update(Genre genre) {
        genreRepository.update(genre);
    }

    @Override
    public void insert(Genre genre) {
        genreRepository.insert(genre);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
