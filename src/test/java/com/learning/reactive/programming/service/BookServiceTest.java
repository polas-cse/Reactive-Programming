package com.learning.reactive.programming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();

    private BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Bangla", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
            })
                .assertNext(book -> {
                    assertEquals("English", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
            })
                .assertNext(book -> {
                    assertEquals("Math", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
            }).verifyComplete();

    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1).log();
        StepVerifier.create(book).assertNext(b->{
            assertEquals("Math", b.getBookInfo().getTitle());
            assertEquals(2, b.getReviews().size());
        }).verifyComplete();
    }


}
















