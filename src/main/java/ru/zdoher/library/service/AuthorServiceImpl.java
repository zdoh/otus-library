package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.repositories.AuthorRepository;
import ru.zdoher.library.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }

    @Override
    public void insert(Author author) {
        authorRepository.insert(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.update(author);
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id) != null;
    }
}
