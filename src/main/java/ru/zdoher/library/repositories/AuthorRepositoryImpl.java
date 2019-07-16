package ru.zdoher.library.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zdoher.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public boolean deleteById(Long id) {
        Author author = getById(id);

        if (author != null) {
            em.remove(author);
            return true;
        }

        return false;

    }

    @Override
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public void update(Author author) {
        em.persist(author);
    }
}
