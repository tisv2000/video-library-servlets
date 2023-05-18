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
    title VARCHAR(256) UNIQUE
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
    title VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(256) NOT NULL,
    birthday   DATE,
    image      VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS movie_person
(
    id        SERIAL PRIMARY KEY,
    movie_id  INT REFERENCES movie (id) NOT NULL,
    person_id INT REFERENCES person (id) NOT NULL,
    role_id   INT REFERENCES person_role (id) NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) NOT NULL,
    birthday DATE NOT NULL,
    password VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    image   VARCHAR(124),
    role     VARCHAR(32)  NOT NULL,
    gender    VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS review
(
    id       SERIAL PRIMARY KEY,
    user_id  INT REFERENCES users (id) NOT NULL,
    movie_id INT REFERENCES movie (id) NOT NULL,
    text     VARCHAR(256) NOT NULL,
    rate     INT NOT NULL
);

select p.name from movie_person inner join person p on p.id = movie_person.person_id where movie_id = 3
