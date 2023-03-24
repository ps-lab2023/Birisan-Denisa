package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class MovieServiceImplementare implements MovieService {
    @Autowired
    private MovieRepository movieRepo;

    public MovieServiceImplementare(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie saveMovie(Movie film) {
        return movieRepo.save(film);
    }

    @Override
    public Movie findByName(String nume) {
        return movieRepo.findFirstByName(nume);
    }

    @Override
    public Movie updateMovie(Movie film, String reg) {
        Movie updateMovie = movieRepo.findById(film.getId()).get();
        updateMovie.setRegizor(reg);
        movieRepo.save(updateMovie);

        return updateMovie;
    }

    @Override
    public Movie updateMovieWatchList(Movie film,WatchList watchList) {
        //aici sterg vechea valoare din watchlist,ca n are de unde sti sa se propage asta
        Movie updateMovie = movieRepo.findFirstByName(film.getName());
        //daca fac asa si sterg prima intrare, pot sa scap de valorile alea dublate de genu:
        //pt un movie caruia ii udpatez watchlistu, imi raman si vechile intrari de watchlist
        //inclusiv cea initiala de null cu toate adnotarile vietii
        movieRepo.delete(updateMovie);
        updateMovie.setWatchList(watchList);
        movieRepo.save(updateMovie);

        return updateMovie;
    }

    @Override
    public Movie updateMovieBiletPretAndLoc(Movie film, int pretNou, int locNou) {
        Movie updateMovie=movieRepo.findFirstByName(film.getName());
        movieRepo.delete(updateMovie);
        Bilet b=updateMovie.getBiletPtFilm();
        b.setPret(pretNou);
        b.setLocInSala(locNou);
        updateMovie.setBiletPtFilm(b);
        movieRepo.save(updateMovie);
        return  updateMovie;
    }


    @Override
    public void deleteMovie(String nume) {
       //cd sterg un movie vr sa se stearga si intrarea null pt el din WatchList
        movieRepo.deleteById(movieRepo.findFirstByName(nume).getId());
    }

    @Override
    public void deleteBiletForMovie(Movie film) {
        this.updateMovieBiletPretAndLoc(film,-1,-1);
    }

    @Override
    public List<Movie> findAllMoviesInWatchList(WatchList watchList) {
        return movieRepo.findAllMoviesByWatchList(watchList);
    }


}
