package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}