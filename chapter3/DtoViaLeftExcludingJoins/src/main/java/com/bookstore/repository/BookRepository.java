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
    // Fetch books and authors excluding books that have registered authors (JPQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Book b LEFT JOIN b.author a WHERE a.id IS NULL")
    List<AuthorNameBookTitle> findBooksAndAuthorsJpql();

    // Fetch books and authors excluding books that have registered authors (SQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM book b LEFT JOIN author a ON a.id = b.author_id WHERE a.id IS NULL",
            nativeQuery = true)
    List<AuthorNameBookTitle> findBooksAndAuthorsSql();
}