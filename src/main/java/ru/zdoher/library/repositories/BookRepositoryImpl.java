package ru.zdoher.library.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zdoher.library.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    //@EntityGraph
    public List<Book> getAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public boolean deleteById(Long id) {
        Book book = em.find(Book.class, id);

        if (book != null) {
            em.remove(book);
            return true;
        }

        return false;
    }

    @Override
    public boolean insert(Book book) {
        try {
            em.persist(book);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
