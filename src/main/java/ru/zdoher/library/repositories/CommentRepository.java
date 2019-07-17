package ru.zdoher.library.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    List<Comment> findCommentsByBook(Book book);

    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END from Comment c WHERE c.book.id = ?1 and id = ?2")
    boolean commentInBookExist(Long idBook, Long idComment);
}
