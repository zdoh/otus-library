package ru.zdoher.library.service;

import ru.zdoher.library.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

/*    List<Comment> getAllForBook(Book book);*/

    boolean deleteById(String id);

    Comment insert(Comment comment);

    /*boolean commentInBookExist(Long idBook, Long idComment);*/
}
