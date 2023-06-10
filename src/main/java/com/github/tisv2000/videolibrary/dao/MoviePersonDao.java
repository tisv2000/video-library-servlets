package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.*;
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
public class MoviePersonDao implements Dao<Integer, MoviePerson> {

    private static final MoviePersonDao INSTANCE = new MoviePersonDao();
    public final GenreDao GENRE = GenreDao.getInstance();

    private static final String SAVE_MOVIE_PERSON_SQL = """
            INSERT INTO movie_person (movie_id, person_id, role_id)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_ALL_BY_MOVIE_ID_SQL = """
            SELECT person.id as personId, person.name, person.birthday, person_role.title as personRoleTitle, person.image as personImage,
                   movie.id as movieId, movie.title, movie.year, movie.country, movie.genre_id, movie.image as movieImage, movie.description
            FROM movie_person
                     INNER JOIN person ON movie_person.person_id = person.id
                     INNER JOIN person_role ON movie_person.role_id = person_role.id
                     INNER JOIN movie on movie.id = movie_person.movie_id
            WHERE movie_id = ?
            """;
    private static final String FIND_ALL_BY_PERSON_ID_SQL = """
            SELECT person.id as personId, person.name, person.birthday, person_role.title as personRoleTitle, person.image as personImage,
                   movie.id as movieId, movie.title, movie.year, movie.country, movie.genre_id, movie.image as movieImage, movie.description
            FROM movie_person
                     INNER JOIN person ON movie_person.person_id = person.id
                     INNER JOIN person_role ON movie_person.role_id = person_role.id
                     INNER JOIN movie on movie.id = movie_person.movie_id
            WHERE person_id = 15?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM movie_person
            WHERE id = ?
            """;

    @SneakyThrows
    @Override
    public void save(MoviePerson entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_MOVIE_PERSON_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setObject(1, entity.getMovie().getId());
            preparedStatement.setObject(2, entity.getPerson().getId());
            preparedStatement.setObject(3, entity.getRole().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
    }

    @SneakyThrows
    public List<MoviePerson> findAllByMovieId(int movieId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_MOVIE_ID_SQL);) {

            preparedStatement.setObject(1, movieId);

            var resultSet = preparedStatement.executeQuery();

            List<MoviePerson> moviePersons = new ArrayList<>();
            while (resultSet.next()) {
                moviePersons.add(build(resultSet));
            }
            return moviePersons;
        }
    }

    @SneakyThrows
    public List<MoviePerson> findAllByMoviePersonId(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_PERSON_ID_SQL);) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            List<MoviePerson> movies = new ArrayList<>();
            while (resultSet.next()) {
                movies.add(build(resultSet));
            }
            return movies;
        }
    }

    @Override
    public boolean update(MoviePerson entity) {
        throw new NotSupportedOperationException("Update operation is not supported for MoviePerson");
    }

    @Override
    public Optional<MoviePerson> findById(Integer id) {
        throw new NotSupportedOperationException("Find by Id operation is not supported for MoviePerson");
    }

    @Override
    public List<MoviePerson> findAll() {
        throw new NotSupportedOperationException("Find All operation is not supported for MoviePerson");
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

    @SneakyThrows
    private MoviePerson build(ResultSet resultSet) throws SQLException {
        int genreId = resultSet.getInt("genre_id");
        return MoviePerson.builder()
                .person(Person
                        .builder()
                        .id(resultSet.getInt("personId"))
                        .name(resultSet.getString("name"))
                        .birthday(resultSet.getDate("birthday").toLocalDate())
                        .image(resultSet.getString("personImage"))
                        .build())
                .role(PersonRole
                        .builder()
                        .title(resultSet.getString("personRoleTitle"))
                        .build())
                .movie(Movie
                        .builder()
                        .id(resultSet.getInt("movieId"))
                        .title(resultSet.getString("title"))
                        .year(resultSet.getInt("year"))
                        .country(resultSet.getString("country"))
                        .genre(GENRE.findById(genreId).orElseThrow(() ->
                                new GenreNotFoundException("Genre with id " + genreId))
                        )
                        .image(resultSet.getString("movieImage"))
                        .description(resultSet.getString("description"))
                        .build())
                .build();
    }

    public static MoviePersonDao getInstance() {
        return INSTANCE;
    }
}
