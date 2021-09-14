package com.thebug.library_system.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailsId;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDate birthDate;


    public Details() {
    }

    public Details(String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getDetailId() {
        return detailsId;
    }

    public void setDetailId(int detailId) {
        this.detailsId = detailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


}
