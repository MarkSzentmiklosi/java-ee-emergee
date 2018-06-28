package com.codecool.amf.model;

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

    private String password;

    @Enumerated
    private ServiceType service;

    @OneToMany(mappedBy = "partner")
    private List<HelpRequest> requests = new ArrayList<>();


    public Partner() {
    }

    public Partner(String name, String email, ServiceType service) {
        this.name = name;
        this.email = email;
        this.service = service;
    }

    public Partner(String name, String email, ServiceType service, String password) {
        this(name, email, service);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
