package com.bookstore;

import com.bookstore.service.BookstoreService;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class MainApplication {
    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.persistTwoBooks();

            System.out.println("\n\n===========================================");

            System.out.println("First call fetchBook() ................");
            bookstoreService.fetchBook();

            System.out.println("Second call fetchBook() ................");
            bookstoreService.fetchBook();

            System.out.println("\n\n===========================================");

            System.out.println("First call fetchBookByPrice() ................");
            bookstoreService.fetchBookByPrice();

            System.out.println("Second call fetchBookByPrice() ................");
            bookstoreService.fetchBookByPrice();
        };
    }
}