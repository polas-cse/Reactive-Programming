package com.learning.reactive.programming.service;

import com.learning.reactive.programming.domain.Book;
import com.learning.reactive.programming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks(){
        var books = List.of(
                new BookInfo(1, "Bangla", "Polas", "12345"),
                new BookInfo(2, "English", "Naime", "12346") ,
                new BookInfo(3, "Math", "Shopon", "12347")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId){
        var book = new BookInfo(bookId,"Math", "Shopon", "12347");
        return Mono.just(book);
    }

}
