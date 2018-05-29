package com.codecool.amf.model;

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
}
