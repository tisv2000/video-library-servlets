package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Gender;
import com.github.tisv2000.videolibrary.entity.Role;
import com.github.tisv2000.videolibrary.entity.User;
import com.github.tisv2000.videolibrary.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, password, email, image, role, gender)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT * FROM users WHERE email=? AND password=?
            """;

    private static final String FIND_BY_EMAIL_SQL = """
            SELECT * FROM users WHERE email=?
            """;

    @Override
    @SneakyThrows
    public void save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getEmail());
            preparedStatement.setObject(5, entity.getImage());
            preparedStatement.setObject(6, entity.getRole().name());
            preparedStatement.setObject(7, entity.getGender().name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
    }

    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL);) {
            preparedStatement.setObject(1, email);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(build(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL);) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(build(resultSet));
            } else {
                return Optional.empty();
            }

        }
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    private User build(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .email(resultSet.getString("email"))
                .role(Role.valueOf(resultSet.getString("role")))
                .image(resultSet.getString("image"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
