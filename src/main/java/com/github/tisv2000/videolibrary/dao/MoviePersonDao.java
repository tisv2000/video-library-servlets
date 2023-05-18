package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.MoviePerson;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.entity.PersonRole;
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

    private static final String SAVE_MOVIE_PERSON_SQL = """
            INSERT INTO movie_person (movie_id, person_id, role_id)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_ALL_FOR_MOVIE_SQL = """
            SELECT person.id, person.name, person_role.title
                  FROM movie_person
                  INNER JOIN person ON movie_person.person_id = person.id
                  INNER JOIN person_role ON movie_person.role_id = person_role.id
                  WHERE movie_id = ?
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
             var preparedStatement = connection.prepareStatement(FIND_ALL_FOR_MOVIE_SQL);) {

            preparedStatement.setObject(1, movieId);

            var resultSet = preparedStatement.executeQuery();

            List<MoviePerson> moviePersons = new ArrayList<>();
            while (resultSet.next()) {
                moviePersons.add(build(resultSet));
            }
            return moviePersons;
        }
    }

    @Override
    public boolean update(MoviePerson entity) {
        return false;
    }

    @Override
    public Optional<MoviePerson> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<MoviePerson> findAll() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }


    private MoviePerson build(ResultSet resultSet) throws SQLException {
        return MoviePerson.builder()
                .person(Person
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build())
                .role(PersonRole
                        .builder()
                        .title(resultSet.getString("title"))
                        .build())
                .build();
    }

    public static MoviePersonDao getInstance() {
        return INSTANCE;
    }
}
