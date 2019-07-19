package ru.zdoher.library.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.zdoher.library.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    // сделал чтобы сохранился FETCH
    //@Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.genre")
    List<Book> findAll();
}
