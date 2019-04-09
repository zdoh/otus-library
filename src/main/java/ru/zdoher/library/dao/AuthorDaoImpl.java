package ru.zdoher.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.zdoher.library.model.Author;

import java.util.Collections;
import java.util.List;

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
        return null;
    }

    @Override
    public Author getById(int id) {
        return null;
    }

    @Override
    public void add(Author author) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void insert(Author author) {

    }
}
