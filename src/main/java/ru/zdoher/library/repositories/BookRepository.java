package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    boolean existsCommentsByIdAndCommentsId(String bookId, String commentId);
}
