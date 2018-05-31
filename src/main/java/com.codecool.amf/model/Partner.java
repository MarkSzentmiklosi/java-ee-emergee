package com.codecool.amf.model;

import com.codecool.amf.PService;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    @Enumerated
    private PService service;

    @OneToMany(mappedBy = "partner")
    private List<HRequest> requests;


    public Partner() {
    }

    public Partner(String name, String email, PService service) {
        this.name = name;
        this.email = email;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PService getService() {
        return service;
    }

    public void setService(PService service) {
        this.service = service;
    }

    public List<HRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<HRequest> requests) {
        this.requests = requests;
    }
}
