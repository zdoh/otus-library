package ru.zdoher.library.service;

import ru.zdoher.library.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean deleteById(String id);

    Comment insert(Comment comment);
}
