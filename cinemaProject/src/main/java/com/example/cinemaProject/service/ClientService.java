package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public interface ClientService {
    Client saveClient(Client client);
    Client findByName(String nume,String prenume);
    Client updateClient(Client client,int age);
    //asta e inutila ca se face automat
    Client updateClientAddMovieToWatchList(Client client, Movie film);
    //asta e leg pe care o doresc, cred
    void updateClientAddMovieToListaMoviesCurente(Client client, Movie film);
    void deleteClient(String nume,String prenume);
}
