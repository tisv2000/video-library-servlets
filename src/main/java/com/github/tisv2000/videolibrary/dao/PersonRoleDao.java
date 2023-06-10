package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.PersonRole;
import com.github.tisv2000.videolibrary.exception.*;
import com.github.tisv2000.videolibrary.util.ConnectionManager;
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
                persons.add(build(resultSet));
            }
            return persons;
        }
    }

    @Override
    public void save(PersonRole entity) {
        throw new NotSupportedOperationException("Save operation is not supported for PersonRole");
    }

    @Override
    public boolean update(PersonRole entity) {
        throw new NotSupportedOperationException("Update operation is not supported for PersonRole");
    }

    @Override
    public Optional<PersonRole> findById(Integer id) {
        throw new NotSupportedOperationException("Find by Id operation is not supported for PersonRole");
    }

    @Override
    public boolean delete(Integer id) {
        throw new NotSupportedOperationException("Delete operation is not supported for PersonRole");
    }


    private PersonRole build(ResultSet resultSet) throws SQLException {
        return PersonRole.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .build();
    }

    public static PersonRoleDao getInstance() {
        return INSTANCE;
    }
}
