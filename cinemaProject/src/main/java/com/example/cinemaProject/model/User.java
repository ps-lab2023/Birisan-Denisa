package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User {
    //aceste atribute le vor avea si clientul si adminu

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nume;
    private  String prenume;
    private int age;


    public User(Long id, String nume, String prenume, int age) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.age = age;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

