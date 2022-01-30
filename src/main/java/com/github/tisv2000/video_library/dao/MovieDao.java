package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.Movie;
import com.github.tisv2000.video_library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDao implements Dao<Integer, Movie> {

    private static final MovieDao INSTANCE = new MovieDao();
    public static final GenreDao GENRE = GenreDao.getInstance();

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_BY_ID_SQL = """
            SELECT id, title, year, country, genre_id
            FROM movie
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title, year, country, genre_id
            FROM movie
            """;

    private static final String SAVE_SQL = """
            INSERT INTO movie (title, year, country, genre_id)
            VALUES (?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE movie
            SET title = ?,
                year = ?,
                country = ?,
                genre_id = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM movie
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public void save(Movie entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getYear());
            preparedStatement.setString(3, entity.getCountry());
            preparedStatement.setInt(4, entity.getGenre().getId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
        }
    }

    @Override
    @SneakyThrows
    public boolean update(Movie entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getYear());
            preparedStatement.setString(3, entity.getCountry());
            preparedStatement.setInt(4, entity.getGenre().getId());
            preparedStatement.setInt(5, entity.getId());

            return preparedStatement.executeUpdate() == 1;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Movie> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);) {

            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(build(resultSet));
            } else {
                return Optional.empty();
            }

        }
    }

    @Override
    @SneakyThrows
    public List<Movie> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (resultSet.next()) {
                movies.add(build(resultSet));
            }
            return movies;
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(DELETE_SQL);) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        }
    }

    private Movie build(ResultSet resultSet) throws SQLException {
        return Movie.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .year(resultSet.getInt("year"))
                .country(resultSet.getString("country"))
                .genre(GENRE.findById(resultSet.getInt("genre_id")).get())
                .build();
    }
}
