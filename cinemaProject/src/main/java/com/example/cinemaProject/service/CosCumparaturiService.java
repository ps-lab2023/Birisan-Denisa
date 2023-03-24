package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CosCumparaturiService {
    //prima iarasi nu o folosesc, ca pun sa mi se adauge practic din Bilet direct
    //toata smecheria asta o fac ca sa pot sa propag modificarile prin tabele natural si automat
    CosCumparaturi saveCosCumparaturi(CosCumparaturi cosCumparaturi);
    //astea 3 le folosesc
    //asta nu, ca se face automat
    void addBiletToCosCumparaturi(Bilet bilet);
    //asta nu acu
    //void removeBletFromCosCumparaturi(Bilet b);
    List<Bilet> findBileteUnderASUm(int sum);
    void deleteAllFromCosCumparaturi();
}
