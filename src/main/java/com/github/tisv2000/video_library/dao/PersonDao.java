package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.dto.PersonFilterDto;
import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.util.ConnectionManager;
import com.github.tisv2000.video_library.util.LocalDateFormatter;
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
public class PersonDao implements Dao<Integer, Person>{

    private static final PersonDao INSTANCE = new PersonDao();

    public static PersonDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_ALL_SQL = """
            SELECT id, name, birth_date
            FROM person
            """;

    private static final String FIND_BY_ID_SQL = """
            INSERT INTO person (name, birth_date)
            VALUES (?, ?)
            """;

    private static final String SAVE_SQL = """
            SELECT id, name, birth_date
            FROM person
            WHERE id=?
            """;

    @SneakyThrows
    @Override
    public void save(Person entity) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.executeUpdate();

            // TODO помедитировать над этим
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
    }

    @Override
    public boolean update(Person entity) {
        return false;
    }

    @SneakyThrows
    @Override
    public Optional<Person> findById(Integer id) {
        try(var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // ??? тут optional только из-за второго return?
                return Optional.of(buildPerson(resultSet));
            }
            return Optional.empty();

        }
    }

    @SneakyThrows
    @Override
    public List<Person> findAll() {
        try(var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<Person> persons = new ArrayList<>();
            while (resultSet.next()) {
                persons.add(buildPerson(resultSet));
            }
            return persons;
        }
    }

    private Person buildPerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birth_date").toLocalDate())
                .build();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @SneakyThrows
    public List<Person> findAllByFilter(PersonFilterDto personFilterDto) {
        List<Object> list = new ArrayList<>();
        int counter = 0;
        String sql = "";

        if (personFilterDto.getName() != null && !personFilterDto.getName().isEmpty()) {
            sql += "AND name=? ";
            list.add(personFilterDto.getName());
            counter++;
        }
        if (personFilterDto.getBirthday() != null && !personFilterDto.getBirthday().isEmpty()) {
            sql += "AND birth_date=? ";
            list.add(LocalDateFormatter.format(personFilterDto.getBirthday()));
            counter++;
        }

        if (!sql.equals("")) {
            sql = FIND_ALL_SQL + sql.replaceFirst("AND", " WHERE");
        }

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql);) {

            for (int i = 0; i < counter; i++) {
                preparedStatement.setObject(i+1, list.get(i));
            }

            var resultSet = preparedStatement.executeQuery();
            List<Person> movies = new ArrayList<>();
            while (resultSet.next()) {
                movies.add(build(resultSet));
            }
            return movies;
        }
    }

    private Person build(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birth_date").toLocalDate())
                .build();
    }
}
