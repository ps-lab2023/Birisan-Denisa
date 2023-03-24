package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.repository.CosCumparaturiRepository;
import com.example.cinemaProject.service.CosCumparaturiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CosCumparaturiServiceImplementare implements CosCumparaturiService {
    @Autowired
    private CosCumparaturiRepository cosCumparaturiRepository;

    public CosCumparaturiServiceImplementare(CosCumparaturiRepository cosCumparaturiRepository) {
        this.cosCumparaturiRepository = cosCumparaturiRepository;
    }

    @Override
    public CosCumparaturi saveCosCumparaturi(CosCumparaturi cosCumparaturi) {
        return cosCumparaturiRepository.save(cosCumparaturi);
    }

    @Override
    public void addBiletToCosCumparaturi(Bilet bilet) {
      //defapt, doar o apelez pt ca nu e chiar oki cum am lucrat eu si sa mi se updateze stuff
        cosCumparaturiRepository.findAll().forEach(cosEntry->
        {
            cosEntry.getBileteDinCosCumparaturi().add(bilet);
            //modifica si totalul in tabel
            int S=cosEntry.calculeazaTotal();
            //si modifica si in repo
            cosCumparaturiRepository.delete(cosEntry);
            cosEntry.setPretTotal(S);

            cosCumparaturiRepository.save(cosEntry);
        });
    }



    @Override
    public List<Bilet> findBileteUnderASUm(int sum) {
        List<Bilet> lista=new ArrayList<Bilet>();
        cosCumparaturiRepository.findAll().forEach(cosEntry->
                {
                    cosEntry.getBileteDinCosCumparaturi().forEach(bilet->
                    {
                        if(bilet.getPret()<sum && bilet.getPret()!=0)
                            lista.add(bilet);
                    });
                });
        return lista;
    }

    @Override
    public void deleteAllFromCosCumparaturi() {
         cosCumparaturiRepository.deleteAll();
    }
}
