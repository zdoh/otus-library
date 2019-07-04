package ru.zdoher.library.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
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
    public Author getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return njdbc.queryForObject("SELECT * FROM author WHERE id = :id", params, new AuthorMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        njdbc.update("DELETE FROM author where id = :id", params);
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> params = Collections.singletonMap("name", author.getName());
        njdbc.update("INSERT INTO author(name) values(:name)", params);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", author.getName());
        params.put("id", author.getId());
        njdbc.update("UPDATE author SET name = :name WHERE id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
