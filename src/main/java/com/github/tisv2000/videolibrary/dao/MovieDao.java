package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.dto.MovieFilterDto;
import com.github.tisv2000.videolibrary.entity.Movie;
import com.github.tisv2000.videolibrary.exception.*;
import com.github.tisv2000.videolibrary.util.ConnectionManager;
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
    public final GenreDao GENRE = GenreDao.getInstance();

    private static final String FIND_BY_ID_SQL = """
            SELECT id, title, year, country, genre_id, image, description
            FROM movie
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title, year, country, genre_id, image, description
            FROM movie
            """;

    private static final String SAVE_SQL = """
            INSERT INTO movie (title, year, country, genre_id, description, image)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE movie
            SET title = ?,
                year = ?,
                country = ?,
                genre_id = ?,
                description = ?,
                image = ?
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
            preparedStatement.setString(5, entity.getDescription());
            preparedStatement.setString(6, entity.getImage());

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
            preparedStatement.setString(5, entity.getDescription());
            preparedStatement.setString(6, entity.getImage());
            preparedStatement.setInt(7, entity.getId());

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

    @SneakyThrows
    public List<Movie> findAllByFilters(MovieFilterDto movieFilterDto) {

        List<Object> predicateValues = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder();

        if (movieFilterDto.getTitle() != null && !movieFilterDto.getTitle().isEmpty()) {
            sqlBuilder.append("AND title=? ");
            predicateValues.add(movieFilterDto.getTitle());
        }
        if (movieFilterDto.getCountry() != null && !movieFilterDto.getCountry().isEmpty()) {
            sqlBuilder.append("AND country=? ");
            predicateValues.add(movieFilterDto.getCountry());
        }
        if (movieFilterDto.getYear() != null && !movieFilterDto.getYear().isEmpty()) {
            sqlBuilder.append("AND year=? ");
            predicateValues.add(Integer.valueOf(movieFilterDto.getYear()));
        }
        if (movieFilterDto.getGenre() != null && !movieFilterDto.getGenre().isEmpty()) {
            sqlBuilder.append("AND genre_id=? ");
            predicateValues.add(Integer.valueOf(movieFilterDto.getGenre()));
        }

        String sql = sqlBuilder.length() > 0
                ? FIND_ALL_SQL + sqlBuilder.replace(0, 4, " WHERE ")
                : FIND_ALL_SQL;

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql);) {

            for (int i = 0; i < predicateValues.size(); i++) {
                preparedStatement.setObject(i + 1, predicateValues.get(i));
            }

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
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL);) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        }
    }


    private Movie build(ResultSet resultSet) throws SQLException {
        int genreId = resultSet.getInt("genre_id");
        return Movie.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .year(resultSet.getInt("year"))
                .country(resultSet.getString("country"))
                .genre(GENRE.findById(genreId).orElseThrow(() ->
                        new GenreNotFoundException("Genre with id " + genreId))
                )
                .image(resultSet.getString("image"))
                .description(resultSet.getString("description"))
                .build();
    }

    public static MovieDao getInstance() {
        return INSTANCE;
    }
}
