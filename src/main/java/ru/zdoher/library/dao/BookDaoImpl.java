package ru.zdoher.library.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookDaoImpl implements BookDao {
    private NamedParameterJdbcOperations njdbc;

    public BookDaoImpl(NamedParameterJdbcOperations njdbc) {
        this.njdbc = njdbc;
    }

    @Override
    public List<Book> getAll() {
        return njdbc.query("SELECT b.id, b.name, a.name as author, b.author_id, g.name as genre, b.genre_id  " +
                "FROM book b " +
                "LEFT JOIN author a ON a.id = b.author_id " +
                "LEFT JOIN genre g ON g.id = b.genre_id", new BookMapping());
    }

    @Override
    public Book getById(Long id) {
        Map<String, Object> result = Collections.singletonMap("id", id);

        try{
            return njdbc.queryForObject("SELECT b.id, b.name, a.name as author, b.author_id, g.name as genre, b.genre_id " +
                 "FROM book b " +
                 "LEFT JOIN author a ON a.id = b.author_id " +
                 "LEFT JOIN genre g ON g.id = b.genre_id WHERE b.id = :id", result, new BookMapping());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> result = Collections.singletonMap("id", id);
        njdbc.update("DELETE FROM book WHERE id = :id", result);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", book.getName());
        result.put("author_id", book.getAuthor().getId());
        result.put("genre_id", book.getGenre().getId());
        njdbc.update("INSERT INTO book(name, author_id, genre_id) " +
                "values(:name, :author_id, :genre_id)", result);
    }

    private static class BookMapping implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("name"),
                    new Author(rs.getLong("author_id"), rs.getString("author")),
                    new Genre(rs.getLong("genre_id"), rs.getString("genre")));
        }
    }
}
