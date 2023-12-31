package com.bookstore.service;

import com.bookstore.projection.AuthorNameBookTitle;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // Fetch books and authors excluding authors that have registered books and books with registered authors (JPQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsJpql() {
        return bookRepository.findBooksAndAuthorsJpql();
    }

    // Fetch books and authors excluding authors that have registered books and books with registered authors (SQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsSql() {
        return bookRepository.findBooksAndAuthorsSql();
    }

    // Fetch authors and books excluding authors that have registered books and books with registered authors (JPQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksJpql() {
        return authorRepository.findAuthorsAndBooksJpql();
    }

    // Fetch authors and books excluding authors that have registered books and books with registered authors (SQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksSql() {
        return authorRepository.findAuthorsAndBooksSql();
    }
}