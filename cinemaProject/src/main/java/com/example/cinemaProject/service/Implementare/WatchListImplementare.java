package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.repository.WatchListRepository;
import com.example.cinemaProject.service.WatchListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class WatchListImplementare implements WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;
    //daca as pune 2 repo-uri as viola principiul  single responsability din SOLID si nu eok!!



    public WatchListImplementare(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

//    @Override
//    public WatchList saveWatchList(WatchList watchList) {
//        //aici trebuie sa fac merge la filme cand mai exista alt watchList pt clientul resp
//        //trebe sa verific daca am asignat deja sau nu un client pt watchlistu respectiv
//        //cam stupid, da asa fac io cod aparent
//        if(watchList.getClient()!=null) {
//            WatchList watchList1 = watchListRepository.findWatchListByClient(watchList.getClient());
//            if (watchList1 != null) {
//                //fac merge la lista de filme
//                Set<Movie> listaMovies = new HashSet<Movie>();
//                for (Movie m : watchList.getListaFilmeDeVazut())
//                    listaMovies.add(m);
//                for (Movie m : watchListRepository.findWatchListByClient(watchList.getClient()).getListaFilmeDeVazut())
//                    listaMovies.add(m);
//                watchList1.setListaFilmeDeVazut(listaMovies);
//                watchListRepository.save(watchList1);
//                return watchList1;
//            }
//            else
//            {
//                watchListRepository.save(watchList);
//                return watchList;
//            }
//        }
//       else //salvez pur si simplu
//        {
//            watchListRepository.save(watchList);
//            return watchList;
//        }
//    }
//
//    @Override
//    public WatchList saveMovieInWatchListForClient(Client client, Movie film) {
//        //aici fac validarea sa nu poata sa fie adaugat in watchlist un film ce nu e in
//        //SAU O FACE AUTOMAT DIN CAUZA RELATIILOR????????
//        //tabelul de movies
////         if(movieRepository.findById(film.getId())!=null)
////         {
//           WatchList watchList=watchListRepository.findWatchListByClient(client);
//           if(watchList!=null) {
//               Set<Movie> noua = new HashSet<Movie>();
//               noua = watchList.getListaFilmeDeVazut();
//               noua.add(film);
//               watchList.setListaFilmeDeVazut(noua);
//               watchListRepository.save(watchList);
//               //noua.stream().forEach(film1-> System.out.println(film1.getName()));
//               return watchList;
//           }
//           else return null;
////         }
////         else return null;  }
////         else return null;
//    }

    @Override
    public List<WatchList> findByClient(Client client) {
        List<WatchList> lista=new ArrayList<>();
         watchListRepository.findAll().forEach(w->
        { //practic ma duc prin toate filmele si cd gasesc unul care
            w.getListaFilmeDeVazut().forEach(movie->
            {
                movie.getClienti().forEach(client1->
                {
                    if (client1.getId()==client.getId())
                        lista.add(w);
                });
            });
        });
         return lista;
    }

    @Override
    public Set<WatchList> findWatchListContainingMovie(Movie film) {
        Set<WatchList> lista=new HashSet<>();
        watchListRepository.findAll().forEach(w->
        {
           // System.out.println(w.getNumeWatchList());

                w.getListaFilmeDeVazut().stream().forEach(movie->
                {
                    if (movie.getName().equals(film.getName())) {

                         lista.add(w);
                    }
                });

        });
        return lista;
    }

    @Override
    public WatchList updateWatchList(WatchList watchList, Movie filmDeInlocuit, Movie filmCuCareSeInlocuieste) {
        if(watchList.getListaFilmeDeVazut().contains(filmDeInlocuit))
        {
            Set<Movie> lista=watchList.getListaFilmeDeVazut();
            lista.remove(filmDeInlocuit);
            lista.add(filmCuCareSeInlocuieste);
            watchList.setListaFilmeDeVazut(lista);
            //asasalvez si in repo,ff imp!!
            watchListRepository.save(watchList);
            return watchList;
        }
        else return null;
    }

    @Override
    public void deleteWatchList(Client client) {
        List<WatchList> watchList=this.findByClient(client);
        if (watchList!=null)
        {
            watchListRepository.deleteAll(watchList);
        }

    }
}
