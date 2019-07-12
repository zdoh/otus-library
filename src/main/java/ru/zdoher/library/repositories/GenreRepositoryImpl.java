package ru.zdoher.library.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zdoher.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public boolean deleteById(Long id) {
        Genre genre = getById(id);

        if (genre != null) {
            em.remove(genre);
            return true;
        }

        return false;
    }

    @Override
    public void insert(Genre genre) {
       em.persist(genre);
    }

    @Override
    public void update(Genre genre) {
        em.merge(genre);
    }

}
