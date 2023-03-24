package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieService  {

    Movie saveMovie(Movie film);
    Movie findByName(String nume);
    Movie updateMovie(Movie film,String reg);
    Movie updateMovieWatchList(Movie film,WatchList watchList);
    Movie updateMovieBiletPretAndLoc(Movie film,int pretNou, int locNou);
    void deleteMovie(String nume);
    void deleteBiletForMovie(Movie film);

    List<Movie> findAllMoviesInWatchList(WatchList byClient);
}
