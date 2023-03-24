package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewService {
    Review saveReview(Review review);
    List<Review> findByStele(int nr);
    Review updateMovie(Review review,String commentNou);
    void deleteReview(int nr);
}
