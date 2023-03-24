package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface WatchListService {
    //astea de save nu le fol veci, ca se propaga din movie
    //deci nu le testez si nimic, da nu sterg nmc momentan
   // WatchList saveWatchList(WatchList watchList);
    //WatchList saveMovieInWatchListForClient(Client client,Movie film);
    //read-urile si delete u si update u,da
    List<WatchList> findByClient(Client client);
    Set<WatchList> findWatchListContainingMovie(Movie film);
    WatchList updateWatchList(WatchList watchList,Movie filmDeInlocuit,
                              Movie filmCuCareSeInlocuieste);
    void deleteWatchList(Client client);
}
