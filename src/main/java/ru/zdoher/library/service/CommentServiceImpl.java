package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.repositories.CommentRepository;
import ru.zdoher.library.domain.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return commentRepository.deleteById(id);
    }

    @Override
    public boolean insert(Comment comment)  {
        return commentRepository.insert(comment);
    }

    @Override
    public boolean commentInBookExist(Long idBook, Long idComment) {
        return commentRepository.commentInBookExist(idBook, idComment);
    }
}
