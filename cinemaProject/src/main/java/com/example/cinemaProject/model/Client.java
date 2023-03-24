package com.example.cinemaProject.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends User {

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn
    private List<Movie> movies;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JoinColumn(name="client_cu_lista_vizionate")
//    private WatchList listaVizionate;

    public Client(Long id, String nume, String prenume, int age, List<Movie> movies) {
        super(id, nume, prenume, age);
        this.movies = new ArrayList<Movie>();
        //this.listaVizionate = new WatchList();
    }
}
