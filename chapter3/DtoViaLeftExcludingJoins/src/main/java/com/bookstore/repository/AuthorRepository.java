package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.projection.AuthorNameBookTitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Fetch authors and books excluding authors that have registered books (JPQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Author a LEFT JOIN a.books b WHERE b.id IS NULL")
    List<AuthorNameBookTitle> findAuthorsAndBooksJpql();

    // Fetch authors and books excluding authors that have registered books (SQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM author a LEFT JOIN book b ON a.id = b.author_id WHERE b.id IS NULL",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksSql();
}