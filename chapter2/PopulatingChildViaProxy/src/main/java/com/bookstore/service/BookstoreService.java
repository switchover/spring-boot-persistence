package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void addBookToAuthorWithFindById() {
        Author author = authorRepository.findById(1L).orElseThrow();

        Book book = new Book();
        book.setIsbn("001-MJ");
        book.setTitle("The Canterbury Anthology");
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional
    public void addBookToAuthor() {
        // behind getOne() we have EntityManager#getReference()
        Author proxy = authorRepository.getOne(1L);

        Book book = new Book();
        book.setIsbn("001-MJ");
        book.setTitle("The Canterbury Anthology");
        book.setAuthor(proxy);

        bookRepository.save(book);
    }
}
