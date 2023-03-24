package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
    Movie findFirstByName(String nume);
    List<Movie> findAllMoviesByWatchList(WatchList watchList);
}
