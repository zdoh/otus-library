package ru.zdoher.library.service;

import org.springframework.stereotype.Service;
import ru.zdoher.library.repositories.CommentRepository;
import ru.zdoher.library.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean deleteById(String id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Comment insert(Comment comment)  {
        return commentRepository.save(comment);
    }

}
