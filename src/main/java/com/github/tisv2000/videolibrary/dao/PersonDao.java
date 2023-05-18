package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.dto.PersonFilterDto;
import com.github.tisv2000.videolibrary.entity.Person;
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
public class PersonDao implements Dao<Integer, Person> {

    private static final PersonDao INSTANCE = new PersonDao();

    private static final String FIND_ALL_SQL = """
            SELECT id, name, birthday, image
            FROM person
            """;

    private static final String SAVE_SQL = """
            INSERT INTO person (name, birthday, image)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id, name, birthday, image
            FROM person
            WHERE id=?
            """;

    private static final String FIND_BY_NAME_SQL = """
            SELECT id, name, birthday, image
            FROM person
            WHERE name=?
            """;

    @SneakyThrows
    @Override
    public void save(Person entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getImage());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
    }

    @SneakyThrows
    @Override
    public List<Person> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {

            var resultSet = preparedStatement.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (resultSet.next()) {
                persons.add(build(resultSet));
            }
            return persons;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Person> findById(Integer id) {
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

    @SneakyThrows
    public List<Person> findAllByName(PersonFilterDto personFilterDto) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL);) {
            preparedStatement.setObject(1, personFilterDto.getName());

            var resultSet = preparedStatement.executeQuery();

            List<Person> movies = new ArrayList<>();
            while (resultSet.next()) {
                movies.add(build(resultSet));
            }
            return movies;
        }
    }

    @Override
    public boolean update(Person entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }


    private Person build(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .image(resultSet.getString("image"))
                .build();
    }

    public static PersonDao getInstance() {
        return INSTANCE;
    }
}
