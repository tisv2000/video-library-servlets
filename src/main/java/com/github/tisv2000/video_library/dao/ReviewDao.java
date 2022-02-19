package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.*;
import com.github.tisv2000.video_library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDao implements Dao<Integer, Review> {

    private static final ReviewDao INSTANCE = new ReviewDao();

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
//
//    private static final String FIND_ALL_SQL = """
//            SELECT id, user_id, movie_id, text, rate
//            FROM review
//            """;

    // user: id, name, birthday, password, email, image, role, gender
    // movie: id, title, year, country, genre_id, image, description
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
    public List<Review> findAllWithMovieId(int movieId) {
        var sql = FIND_ALL_SQL + " WHERE r.movie_id = ?";

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setObject(1, movieId);
            return findAllWith(preparedStatement);
        }
    }

    @SneakyThrows
    public List<Review> findAllWithUserId(int userId) {
        var sql = FIND_ALL_SQL + " WHERE u.user_id = ?";

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setObject(1, userId);
            return findAllWith(preparedStatement);
        }
    }

    @Override
    public Optional<Review> findById(Integer id) {
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public List<Review> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            return findAllWith(preparedStatement);
        }
    }

    @SneakyThrows
    public List<Review> findAllWith(PreparedStatement preparedStatement) {
        var resultSet = preparedStatement.executeQuery();
        List<Review> reviews = new ArrayList<>();
        while (resultSet.next()) {
            reviews.add(Review.builder()
                    .id(resultSet.getInt("reviewId"))
                    // для юзера не все поля возвращаем
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
                    .build());
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
    public boolean update(Review entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

}
