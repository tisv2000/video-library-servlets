package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.*;
import com.github.tisv2000.videolibrary.exception.*;
import com.github.tisv2000.videolibrary.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDao implements Dao<Integer, Review> {

    private static final ReviewDao INSTANCE = new ReviewDao();

    private static final String FIND_ALL_SQL = """
            SELECT  r.id AS reviewId, r.rate as reviewRate, r.text as reviewText,
                    u.id AS userId, u.name AS userName, u.birthday AS userBirthday, u.email AS userEmail, u.role AS userRole, u.gender AS userGender,
                    m.id AS movieId, m.title AS movieTitle, m.year AS movieYear, m.country AS movieCountry, m.description AS movieDescription,
                    g.id AS genreId, g.title AS genreTitle
            FROM review r
                     INNER JOIN users u ON u.id = r.user_id
                     INNER JOIN movie m ON m.id = r.movie_id
                     INNER JOIN genre g ON g.id = m.genre_id
            """;

    private static final String SAVE_SQL = """
            INSERT INTO review (user_id, movie_id, text, rate)
            VALUES (?, ?, ?, ?)
            """;

    @SneakyThrows
    public List<Review> findAllByMovieId(int movieId) {
        var sql = FIND_ALL_SQL + " WHERE r.movie_id = ?";

        return findByCustomId(sql, movieId);
    }

    @SneakyThrows
    public List<Review> findAllByUserId(int userId) {
        var sql = FIND_ALL_SQL + " WHERE r.user_id = ?";

        return findByCustomId(sql, userId);
    }

    private List<Review> findByCustomId(String sql, int customId) throws SQLException {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setObject(1, customId);

            return findAllByPreparedStatement(preparedStatement);
        }
    }

    @SneakyThrows
    @Override
    public List<Review> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {

            return findAllByPreparedStatement(preparedStatement);
        }
    }

    @SneakyThrows
    private List<Review> findAllByPreparedStatement(PreparedStatement preparedStatement) {

        var resultSet = preparedStatement.executeQuery();

        List<Review> reviews = new ArrayList<>();
        while (resultSet.next()) {
            reviews.add(build(resultSet));
        }
        return reviews;
    }

    @SneakyThrows
    @Override
    public void save(Review entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setObject(1, entity.getUser().getId());
            preparedStatement.setObject(2, entity.getMovie().getId());
            preparedStatement.setObject(3, entity.getText());
            preparedStatement.setObject(4, entity.getRate());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
    }

    @Override
    public Optional<Review> findById(Integer id) {
        throw new NotSupportedOperationException("Find by Id operation is not supported for Review");
    }

    @Override
    public boolean update(Review entity) {
        throw new NotSupportedOperationException("Update operation is not supported for Review");
    }

    @Override
    public boolean delete(Integer id) {
        throw new NotSupportedOperationException("Delete operation is not supported for Review");
    }


    private Review build(ResultSet resultSet) throws SQLException {
        return Review.builder()
                .id(resultSet.getInt("reviewId"))
                .user(User.builder()
                        .id(resultSet.getInt("userId"))
                        .name(resultSet.getString("userName"))
                        .birthday(resultSet.getDate("userBirthday").toLocalDate())
                        .email(resultSet.getString("userEmail"))
                        .role(Role.valueOf(resultSet.getString("userRole")))
                        .gender(Gender.valueOf(resultSet.getString("userGender")))
                        .build())
                .movie(Movie.builder()
                        .id(resultSet.getInt("movieId"))
                        .title(resultSet.getString("movieTitle"))
                        .year(resultSet.getInt("movieYear"))
                        .country(resultSet.getString("movieCountry"))
                        .description(resultSet.getString("movieDescription"))
                        .build())
                .text(resultSet.getString("reviewText"))
                .rate(resultSet.getInt("reviewRate"))
                .build();
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
