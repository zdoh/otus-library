package ru.zdoher.library.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    List<Comment> findCommentsByBook(Book book);

    boolean existsByIdAndBookId(Long id, Long bookId);
}
