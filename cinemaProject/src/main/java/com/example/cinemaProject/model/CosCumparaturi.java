package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CosCumparaturi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int pretTotal;

    @OneToMany(mappedBy = "cosCumparaturi",fetch=FetchType.EAGER)
    private List<Bilet> bileteDinCosCumparaturi=new ArrayList<>();

    public int getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(int pretTotal) {
        this.pretTotal = pretTotal;
    }

    public int calculeazaTotal()
    { int S=0;
        if (!this.getBileteDinCosCumparaturi().isEmpty()) {
            for (Bilet b : this.getBileteDinCosCumparaturi()) {
                if (b!=null)
                S += b.getPret();
            }
        }
        return S;
    }

}
