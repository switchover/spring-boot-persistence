package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void insertAuthor() {
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);

        authorRepository.save(author);
    }
}