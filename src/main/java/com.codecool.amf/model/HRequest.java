package com.codecool.amf.model;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class HRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @Column(name = "creation_date")
    private Timestamp requestCreationDate;
    private boolean active = true;
    @ManyToOne
    private Partner partner;
    @OneToOne
    private Coordinate coordinate;
    @Transient
    private Address address;

}

