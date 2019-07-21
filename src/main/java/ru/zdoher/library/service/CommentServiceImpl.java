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
        return commentRepository.findAll();
    }

/*    @Override
    public List<Comment> getAllForBook(Book book) {
        return commentRepository.findCommentsByBook(book);
    }*/

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

/*    @Override
    public boolean commentInBookExist(String id, String bookId) {
        return commentRepository.existsByIdAndBookId(id, bookId);
    }*/
}
