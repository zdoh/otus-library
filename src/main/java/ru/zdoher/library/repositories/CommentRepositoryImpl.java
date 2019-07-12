package ru.zdoher.library.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zdoher.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> getAll() {
        return em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public boolean insert(Comment comment) {
        try {
            em.persist(comment);
            return true;
        } catch (DataAccessException e) {
            return false;
        }

    }

    @Override
    public boolean deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);

        if (comment != null) {
            em.remove(comment);
            return true;
        }

        return false;
    }

    @Override
    public void update(Comment comment) {
        em.merge(comment);
    }

    @Override
    public boolean commentInBookExist(Long idBook, Long idComment) {
        Query query = em.createQuery("SELECT count(c) FROM Comment c where c.book.id = :book_id and c.id = :id");
        query.setParameter("id", idComment);
        query.setParameter("book_id", idBook);
        return (Long) query.getSingleResult() != 0;
    }
}
