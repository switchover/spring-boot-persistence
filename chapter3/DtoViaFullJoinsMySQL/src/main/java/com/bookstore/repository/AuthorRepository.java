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
    // Fetch all authors and books (SQL)
    @Query(value = "(SELECT b.title AS title, a.name AS name FROM author a "
            + "LEFT JOIN book b ON a.id = b.author_id) "
            + "UNION " //  will remove duplicates (use UNION ALL to keep duplicates)
            + "(SELECT b.title AS title, a.name AS name FROM author a "
            + "RIGHT JOIN book b ON a.id = b.author_id "
            + "WHERE a.id IS NULL)",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksSql();
}