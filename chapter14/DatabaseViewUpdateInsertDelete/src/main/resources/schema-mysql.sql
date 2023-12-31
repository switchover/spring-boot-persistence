-- DML
DROP TABLE IF EXISTS `modification` ^;
DROP TABLE IF EXISTS `chapter` ^;
DROP TABLE IF EXISTS `review` ^;
DROP TABLE IF EXISTS `bestseller` ^;
DROP TABLE IF EXISTS `paperback` ^;
DROP TABLE IF EXISTS `ebook` ^;
DROP TABLE IF EXISTS `author_book` ^;
DROP TABLE IF EXISTS `author_books` ^;
DROP TABLE IF EXISTS `book_review` ^;
DROP TABLE IF EXISTS `book` ^;
DROP TABLE IF EXISTS `author` ^;
DROP TABLE IF EXISTS `publisher` ^;

CREATE TABLE author (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    age int(11) NOT NULL,
    genre varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    sellrank int(11) NOT NULL DEFAULT -1,
    royalties int(11) NOT NULL DEFAULT -1,
    rating int(11) NOT NULL DEFAULT -1,
    PRIMARY KEY (id)
) ^;
