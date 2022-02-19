DROP TABLE IF EXISTS movie_person;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS person_role;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS genre
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS movie
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(256) NOT NULL,
    year        INT,
    country     VARCHAR(256) NOT NULL,
    genre_id    INT REFERENCES genre (id),
    image       VARCHAR(128),
    description VARCHAR(512)
);


CREATE TABLE IF NOT EXISTS person_role
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(256) NOT NULL,
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS movie_person
(
    id        SERIAL PRIMARY KEY,
    movie_id  INT REFERENCES movie (id),
    person_id INT REFERENCES person (id),
    role_id   INT REFERENCES person_role (id)
);


CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) NOT NULL,
    birthday DATE NOT NULL,
    password VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    image   VARCHAR(124) NOT NULL,
    role     VARCHAR(32)  NOT NULL,
    gender    VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS review
(
    id       SERIAL PRIMARY KEY,
    user_id  INT REFERENCES users (id),
    movie_id INT REFERENCES movie (id),
    text     VARCHAR(256),
    rate     INT
);


SELECT  r.id AS resultId, r.rate as resultRate, r.text as resultText,
        u.id AS userId, u.name AS userName, u.birthday AS userBirthday, u.email AS userEmail, u.role AS userRole, u.gender AS userGender,
        m.id AS movieId, m.title AS movieTitle, m.year AS movieYear, m.country AS movieCountry, m.description AS movieDescription,
        g.id AS genreId, g.title AS genreTitle
FROM review r
         INNER JOIN users u ON u.id = r.user_id
         INNER JOIN movie m ON m.id = r.movie_id
         INNER JOIN genre g ON g.id = m.genre_id
WHERE r.movie_id = 3
