package ru.zdoher.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public List<Book> getAllByAuthorId(int bookId) {
        return null;
    }

    private static class BookMapping implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("genre"));
        }
    }
}
