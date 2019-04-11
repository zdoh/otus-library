package ru.zdoher.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Service
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations njdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations njdbc) {
        this.njdbc = njdbc;
    }

    @Override
    public int count() {
        return njdbc.queryForObject("SELECT count(*) FROM author", Collections.emptyMap(), Integer.class);
    }

    @Override
    public List<Author> getAll() {
        return njdbc.query("SELECT * FROM author", new AuthorMapper());
    }

    @Override
    public Author getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return njdbc.queryForObject("SELECT * FROM author WHERE id = :id", params, new AuthorMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        njdbc.update("DELETE FROM author where id = :id", params);
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> params = Collections.singletonMap("name", author.getName());
        njdbc.update("INSERT INTO author(name) values(:name)", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getInt("id"), rs.getString("name"));
        }
    }
}
