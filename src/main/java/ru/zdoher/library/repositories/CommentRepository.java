package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

}
