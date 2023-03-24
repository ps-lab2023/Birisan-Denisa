package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.BiletRepository;
import com.example.cinemaProject.service.BiletService;
import org.springframework.beans.factory.annotation.Autowired;

public class BiletServiceImplementare implements BiletService {
    @Autowired
    private BiletRepository biletRepository;

    public BiletServiceImplementare(BiletRepository biletRepository) {
        this.biletRepository = biletRepository;
    }

    @Override
    public Bilet saveBilet(Bilet bilet) {
        return biletRepository.save(bilet);
    }

    @Override
    public Bilet findBiletByNamePriceLoc(Movie film, int price, int loc) {
        return biletRepository.findByPretAndFilmDinBiletAndLocInSala(price,film,loc);
    }

    @Override
    public Bilet updatePretBilet(Bilet bilet, int pretNou) {
           Bilet bilet1=biletRepository.findById(bilet.getId()).get();
           //biletRepository.delete(bilet);
           bilet1.setPret(pretNou);
           biletRepository.save(bilet1);
           return bilet1;

    }

    @Override
    public void updateLocBilet(Bilet bilet, int loc) {
        Bilet bilet1=biletRepository.findById(bilet.getId()).get();
        biletRepository.delete(bilet);
        bilet1.setLocInSala(loc);
        biletRepository.save(bilet1);
    }

    @Override
    public void deleteBilet(Bilet bilet) {
          biletRepository.delete(bilet);
    }

}
