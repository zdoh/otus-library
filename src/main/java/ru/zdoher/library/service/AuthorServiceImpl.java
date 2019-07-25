package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.exception.NotFoundException;
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
        return authorRepository.findAll();
    }

    @Override
    public Author getById(String id) {
        return authorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public boolean deleteById(String id) {
        if (isExist(id)) {
            authorRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public void insert(Author author) {
        authorRepository.insert(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }


    @Override
    public boolean isExist(String id) {
        boolean a = authorRepository.existsById(id);
        System.out.println(id + " - " +a);
        return a;
    }
}
