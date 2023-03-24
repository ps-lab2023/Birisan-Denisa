package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosCumparaturiRepository extends CrudRepository<CosCumparaturi,Long> {
}
