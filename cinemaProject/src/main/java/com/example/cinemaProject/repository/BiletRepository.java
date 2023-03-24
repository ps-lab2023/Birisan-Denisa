package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiletRepository extends CrudRepository<Bilet,Long> {
    Bilet findByPretAndFilmDinBiletAndLocInSala(int pret, Movie film, int loc);
}
