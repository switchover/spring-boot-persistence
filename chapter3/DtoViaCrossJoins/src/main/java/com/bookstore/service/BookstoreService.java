package com.bookstore.service;

import com.bookstore.projection.BookTitleAndFormatType;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.FormatRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookstoreService {
    private final FormatRepository formatRepository;
    private final BookRepository bookRepository;

    public BookstoreService(FormatRepository formatRepository, BookRepository bookRepository) {
        this.formatRepository = formatRepository;
        this.bookRepository = bookRepository;
    }

    // Cross join books and formats (JPQL)
    public List<BookTitleAndFormatType> fetchBooksAndFormatsJpql() {
        return bookRepository.findBooksAndFormatsJpql();
    }

    // Cross join books and formats (SQL)
    public List<BookTitleAndFormatType> fetchBooksAndFormatsSql() {
        return bookRepository.findBooksAndFormatsSql();
    }

    // Cross join formats and books (JPQL)
    public List<BookTitleAndFormatType> fetchFormatsAndBooksJpql() {
        return formatRepository.findFormatsAndBooksJpql();
    }

    // Cross join formats and books (SQL)
    public List<BookTitleAndFormatType> fetchFormatsAndBooksSql() {
        return formatRepository.findFormatsAndBooksSql();
    }
}