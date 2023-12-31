package com.bookstore.repository;

import com.bookstore.entity.Book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface BookRepositoryEntityGraph extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Override
    @EntityGraph(attributePaths = {"author.publisher"})
    Optional<Book> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"author.publisher"})
    List<Book> findAll();

    @Override
    @EntityGraph(attributePaths = {"author.publisher"})
    List<Book> findAll(Specification<Book> spec);
}