package ru.zdoher.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
