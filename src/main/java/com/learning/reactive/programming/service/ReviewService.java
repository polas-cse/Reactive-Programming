package com.learning.reactive.programming.service;

import com.learning.reactive.programming.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId){
        var reviewList = List.of(
                new Review(1, bookId,9.1, "Good Book"),
                new Review(2, bookId,8.7, "Worth Reading")
        );
        return Flux.fromIterable(reviewList);
    }

}
