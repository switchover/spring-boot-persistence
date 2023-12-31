package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.projection.AuthorNameBookTitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {
    // Fetch books and authors including authors that have no registered books and books with no registered authors (JPQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Book b FULL JOIN b.author a")
    List<AuthorNameBookTitle> findBooksAndAuthorsJpql();

    // Fetch books and authors including authors that have no registered books and books with no registered authors (SQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM book b FULL JOIN author a ON a.id = b.author_id",
            nativeQuery = true)
    List<AuthorNameBookTitle> findBooksAndAuthorsSql();
}