package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Transactional
@Service
public class ClientServiceImplementare implements ClientService {
 @Autowired
 private ClientRepository clientRepository;

    public ClientServiceImplementare(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findByName(String nume,String prenume) {
        return clientRepository.findFirstByNumeAndPrenume(nume,prenume);
    }

    @Override
    public Client updateClient(Client client, int age) {
        client.setAge(age);
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClientAddMovieToWatchList(Client client, Movie film) {
        //nuj daca e ok asa cu present
        if(clientRepository.findById(client.getId()).isPresent())
        {
            //scot watchlistu existent din client
            WatchList m= new WatchList();
           // if(client.getListaVizionate()!=null) {
           //     m = client.getListaVizionate();
          //  }
            //din obiectu de tip watchlist scot lista de filme
            Set<Movie> lista=new HashSet<Movie>();
            if(m.getListaFilmeDeVazut()!=null) {
                 lista = m.getListaFilmeDeVazut();
            }
            lista.add(film);
            //i adaug filmu vrut de mn
            m.setListaFilmeDeVazut(lista);
            //il pun in ob de tip Watchlist si updatez clientu meu plus propag in repo
           // client.setListaVizionate(m);
           // client.getListaVizionate().getListaFilmeDeVazut().stream().forEach(movie->
             //       System.out.println(movie.getName()));
            clientRepository.save(client);
            return client;
        }
        return null;
    }

    @Override
    public void updateClientAddMovieToListaMoviesCurente(Client client, Movie film) {
        //cred ca sterg iara lista ant de filme, o modific si o pun pe cea noua
        //sa nu am duplicate

        Client client1=clientRepository.findFirstByNumeAndPrenume(client.getNume(),client.getPrenume());
        List<Movie> lista=new ArrayList<>();
        lista.addAll(client1.getMovies());
        lista.add(film);
        //clientRepository.delete(client1);
        client1.setMovies(lista);
        clientRepository.save(client1);


    }

    @Override
    public void deleteClient(String nume, String prenume) {
            clientRepository.delete(clientRepository.findFirstByNumeAndPrenume(nume,prenume));
    }


}
