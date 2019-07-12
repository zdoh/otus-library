package ru.zdoher.library.repositories;

import ru.zdoher.library.domain.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAll();

    Comment getById(Long id);

    boolean insert(Comment comment);

    boolean deleteById(Long id);

    void update(Comment comment);

    boolean commentInBookExist(Long idBook, Long idComment);
}
