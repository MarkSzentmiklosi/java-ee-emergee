package com.codecool.amf.model;

import com.codecool.amf.model.PService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    @Enumerated
    private PService service;

    @OneToMany(mappedBy = "partner")
    private List<HRequest> requests = new ArrayList<>();


    public Partner() {
    }

    public Partner(String name, String email, PService service) {
        this.name = name;
        this.email = email;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
