package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;

import static com.bookstore.service.AuthorSpecs.isAgeGt45;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Slice<Author> fetchNextSlice(int page, int size) {
        // hint example
        // Map<String, Object> hints = new HashMap<>();
        // hints.put("org.hibernate.readOnly", true);
        return authorRepository.findAll(isAgeGt45(),
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")),
                LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}