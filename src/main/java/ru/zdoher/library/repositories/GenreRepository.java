package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}
