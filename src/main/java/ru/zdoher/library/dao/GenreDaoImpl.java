package ru.zdoher.library.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Service
public class GenreDaoImpl implements GenreDao {
    private NamedParameterJdbcOperations njdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations njdbc) {
        this.njdbc = njdbc;
    }

    @Override
    public int count() {
        return njdbc.queryForObject("SELECT count(*) FROM genre", Collections.emptyMap(), Integer.class);
    }

    @Override
    public List<Genre> getAll() {
        return njdbc.query("SELECT * FROM genre", new GenreMapper());
    }

    @Override
    public Genre getById(int id) {
        Map<String, Object> result = Collections.singletonMap("id", id);
        try {
            return njdbc.queryForObject("SELECT * FROM genre WHERE id = :id", result, new GenreMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> result = Collections.singletonMap("id", id);
        njdbc.update("DELETE FROM genre WHERE id = :id", result);
    }

    @Override
    public void insert(Genre genre) {
        Map<String, Object> result = Collections.singletonMap("name", genre.getName());
        njdbc.update("INSERT INTO genre(name) values(:name)", result);
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", genre.getName());
        params.put("id", genre.getId());
        njdbc.update("UPDATE genre SET name = :name WHERE id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getInt("id"), rs.getString("name"));
        }
    }
}
