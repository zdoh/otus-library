package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.zdoher.library.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
