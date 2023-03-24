package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String regizor;

    //daca mai ai timp si chef
   // private List<Actor> cast;
    @ManyToMany(fetch=FetchType.EAGER,mappedBy = "movies")
    private List<Admin> admini=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Client> clienti=new ArrayList<>();

    //aici am pus cu remove sa imi stearga automat si review-urile daca filmul resp nu mai exista
    //daca pui fetch EAGER aici, cauta repede un review sa ii puna,da n are ca inca nu e creat
    // si da eroare de entity not found
    @OneToMany(mappedBy = "filmReviewed",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Review> listaReviews=new ArrayList<>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_movies_watchlist")
    private WatchList watchList=new WatchList();

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_film_to_bilet")
    //AICI ASA IL OBLIGI SA ITI CREEZE AUTOMAT SI UN BIELT PT FILM, CU INSTANTIEREA ASTA
    //si by default, pt orice film se va crea un bilet null
    private Bilet biletPtFilm=new Bilet();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegizor() {
        return regizor;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name) && Objects.equals(regizor, movie.regizor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regizor);
    }
}
