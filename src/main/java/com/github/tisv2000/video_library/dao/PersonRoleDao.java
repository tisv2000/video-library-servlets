package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.PersonRole;
import com.github.tisv2000.video_library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRoleDao implements Dao<Integer, PersonRole> {

    private static final PersonRoleDao INSTANCE = new PersonRoleDao();

    public static PersonRoleDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_ALL_SQL = """
            SELECT id, title
            FROM person_role
            """;

    @SneakyThrows
    @Override
    public List<PersonRole> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            List<PersonRole> persons = new ArrayList<>();
            while (resultSet.next()) {
                persons.add(buildPerson(resultSet));
            }
            return persons;
        }
    }

    @Override
    public void save(PersonRole entity) {}

    @Override
    public boolean update(PersonRole entity) {
        return false;
    }

    @Override
    public Optional<PersonRole> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    private PersonRole buildPerson(ResultSet resultSet) throws SQLException {
        return PersonRole.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .build();
    }

}
