INSERT INTO genre (title)
VALUES ('Fantasy'),
       ('Fiction'),
       ('Action'),
       ('Science fiction'),
       ('Thriller'),
       ('Drama'),
       ('Romance'),
       ('Adventure'),
       ('Musical');

INSERT INTO person_role (title)
VALUES ('Producer'),
       ('Direction'),
       ('Actor'),
       ('Composer');



INSERT INTO person (name, birth_date)
VALUES ('Emma Watson', '1990-04-25'),
       ('Brad Pitt', '1963-12-18'),
       ('Danial Radcliffe', '1989-07-23'),
       ('Rupert Grint', '1988-08-24'),
       ('Russel Crowe', '1964-04-07'),
       ('Angelina Jolie', '1975-06-04'),
       ('Tom Cruise', '1962-07-03'),
       ('Peter Jackson', '1961-10-31'),
       ('Orlando Bloom', '1977-01-13'),
       ('Hans Zimmer', '1957-09-12'),
       ('Johnny Depp', '1963-06-09'),
       ('Cameron Diaz', '1972-08-30'),
       ('Kate Winslet', '1975-10-05'),
       ('Nina Dobrev', '1989-01-09'),
       ('Ian Somerhalder', '1978-12-08'),
       ('Paul Wesley', '1982-07-23'),
       ('Zac Efron', '1987-10-18'),
       ('Zendaya', '1996-09-01'),
       ('Hugh Jackman', '1968-10-12');

INSERT INTO movie (title, year, country, genre_id)
VALUES ('The Holiday', 2006, 'the USA', 7),
       ('Titanic', 1997, 'the USA', 6),
       ('The Vampire Diaries', 2009, 'the USA', 5),
       ('The Lord of the Rings: The Fellowship of the Ring', 2001, 'New Zealand', 1),
       ('The Lord of the Rings: The Two Towers', 2002, 'New Zealand', 1),
       ('The Lord of the Rings: The Return of the King', 2003, 'New Zealand', 1),
       ('Pirates of the Caribbean: The Curse of the Black Pearl', 2003, 'the USA', 8),
       ('The Greatest Showman', 2017, 'the USA', 9),
       ('Another Cinderella Story', 2008, 'the USA', 9),
       ('Harry Potter and the Sorcerer''s Stone', 2001, 'United Kingdom', 1),
       ('Harry Potter and the Chamber of Secrets', 2002, 'United Kingdom', 1),
       ('Harry Potter and the Prisoner of Azkaban', 2004, 'United Kingdom', 1),
       ('Harry Potter and the Goblet of Fire', 2005, 'United Kingdom', 1),
       ('Harry Potter and the Order of the Phoenix', 2007, 'United Kingdom', 1),
       ('Harry Potter and the Half-Blood Prince', 2009, 'United Kingdom', 1),
       ('Harry Potter and the Deathly Hallows: Part 1', 2010, 'United Kingdom', 1),
       ('Harry Potter and the Deathly Hallows: Part 2', 2011, 'United Kingdom', 1);

INSERT INTO movie_person (movie_id, person_id, role_id)
VALUEs (1, 10, 4),
       (1, 12, 3),
       (1, 13, 3);
