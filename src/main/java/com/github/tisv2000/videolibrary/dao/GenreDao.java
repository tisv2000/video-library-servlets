package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Genre;
import com.github.tisv2000.videolibrary.exception.DaoException;
import com.github.tisv2000.videolibrary.util.ConnectionManager;
import java.util.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDao implements Dao<Integer, Genre> {
    private static final GenreDao INSTANCE = new GenreDao();

    private static final Map<Integer, Genre> cache = new HashMap<>();

    private static final String FIND_ALL_SQL = """
            SELECT id, title
            FROM genre
            """;

    @Override
    @SneakyThrows
    public Optional<Genre> findById(Integer id) {
        checkCache();
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    @SneakyThrows
    public List<Genre> findAll() {
        checkCache();
        return new ArrayList<>(cache.values());
    }

    @SneakyThrows
    private void checkCache() {
        if (cache.isEmpty()) {
            synchronized (this) {
                if (cache.isEmpty()) {
                    fillCache();
                }
            }
        }
    }

    @SneakyThrows
    private void fillCache() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var genre = build(resultSet);
                cache.put(genre.getId(), genre);
            }
        }
    }

    @Override
    public void save(Genre entity) {
        throw new DaoException("This function should not be called for predefined table");
    }

    @Override
    public boolean update(Genre entity) {
        throw new DaoException("This function should not be called for predefined table");
    }

    @Override
    public boolean delete(Integer id) {
        throw new DaoException("This function should not be called for predefined table");
    }


    private Genre build(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .build();
    }

    public static GenreDao getInstance() {
        return INSTANCE;
    }
}
