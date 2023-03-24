package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.Review;
import com.example.cinemaProject.repository.ReviewRepository;
import com.example.cinemaProject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImplementare implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImplementare(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findByStele(int nr) {
        List<Review> lista=new ArrayList<Review>();
        for(Review r: reviewRepository.findAll())
        {
            if (r.getStele()==nr) lista.add(r);
        }
        return lista;
    }

    @Override
    public Review updateMovie(Review review, String commentNou) {
        Review updateReview = reviewRepository.findById(review.getId()).get();
        updateReview.setComentariu(commentNou);
        reviewRepository.save(updateReview);

        return updateReview;
    }

    @Override
    public void deleteReview(int nr) {
        reviewRepository.deleteAll(reviewRepository.findAllByStele(nr));
    }
}
