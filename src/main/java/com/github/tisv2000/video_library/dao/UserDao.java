package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.User;
import com.github.tisv2000.video_library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User>{

    public static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, password, email, role, gender)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    @Override
    @SneakyThrows
    public void save(User entity) {
         try(var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
             preparedStatement.setObject(1, entity.getName());
             preparedStatement.setObject(2, entity.getBirthday());
             preparedStatement.setObject(3, entity.getPassword());
             preparedStatement.setObject(4, entity.getEmail());
             preparedStatement.setObject(5, entity.getRole().name());
             preparedStatement.setObject(6, entity.getGender().name());

             preparedStatement.executeUpdate();
             ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             generatedKeys.next();
             entity.setId(generatedKeys.getObject("id", Integer.class));
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

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
