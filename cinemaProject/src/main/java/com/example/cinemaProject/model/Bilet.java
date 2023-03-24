package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "biletPtFilm",fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private Movie filmDinBilet;

    private int pret=0;
    private int locInSala=-1;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_bilet_to_cosCumparaturi")
    private CosCumparaturi cosCumparaturi=new CosCumparaturi();
}
