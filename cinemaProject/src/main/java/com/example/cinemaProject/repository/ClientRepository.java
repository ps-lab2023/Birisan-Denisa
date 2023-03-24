package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {
    Client findFirstByNumeAndPrenume(String nume,String prenume);
}
