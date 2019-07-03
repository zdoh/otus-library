package ru.zdoher.library.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Book;

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
        return njdbc.query("SELECT b.id, b.name, a.name as author, g.name as genre FROM book b " +
                "LEFT JOIN author a ON a.id = b.author_id " +
                "LEFT JOIN genre g ON g.id = b.genre_id", new BookMapping());
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> result = Collections.singletonMap("id", id);

        try{
            return njdbc.queryForObject("SELECT b.id, b.name, a.name as author, g.name as genre FROM book b " +
                "LEFT JOIN author a ON a.id = b.author_id " +
                "LEFT JOIN genre g ON g.id = b.genre_id WHERE b.id = :id", result, new BookMapping());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> result = Collections.singletonMap("id", id);
        njdbc.update("DELETE FROM book WHERE id = :id", result);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", book.getName());
        result.put("author", book.getAuthorName());
        result.put("genre", book.getGenre());
        njdbc.update("INSERT INTO book(name, author_id, genre_id) " +
                "values(:name, " +
                "(SELECT id FROM author WHERE name = :author), " +
                "(SELECT id FROM genre WHERE name = :genre))", result);
    }

    @Override
    public List<Book> getAllByAuthorId(int authorId) {
        Map<String, Object> result = Collections.singletonMap("author_id", authorId);
        return njdbc.query("SELECT * FROM book WHERE author_id = :author_id", result, new BookMapping());
    }

    private static class BookMapping implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("genre"));
        }
    }
}
