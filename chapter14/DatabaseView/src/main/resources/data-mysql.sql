insert into author (age, name, genre, id) values (23, "Mark Janel", "Anthology", 1) ^;
insert into author (age, name, genre, id) values (43, "Olivia Goy", "Horror", 2) ^;
insert into author (age, name, genre, id) values (51, "Quartis Young", "Anthology", 3) ^;
insert into author (age, name, genre, id) values (34, "Joana Nimar", "History", 4) ^;
insert into book (isbn, title, author_id, id) values ("001-JN", "A History of Ancient Prague", 4, 1) ^;
insert into book (isbn, title, author_id, id) values ("002-JN", "A People's History", 4, 2) ^;
insert into book (isbn, title, author_id, id) values ("001-MJ", "The Beatles Anthology", 1, 3) ^;
insert into book (isbn, title, author_id, id) values ("001-OG", "Carrie", 2, 4) ^;

CREATE OR REPLACE VIEW GENRE_AND_TITLE_VIEW
AS
SELECT
    a.genre,
    b.title
FROM
    author a
        INNER JOIN
    book b ON b.author_id = a.id ^;