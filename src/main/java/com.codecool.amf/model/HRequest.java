package com.codecool.amf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class HRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean active = true;

    @Column(name = "creation_date")
    private Timestamp requestCreationDate;

    @ManyToOne
    private Partner partner;

    @OneToOne
    private Location location;

    @ManyToOne
    private User user;


    public HRequest() {
    }

    public HRequest(boolean active, Timestamp requestCreationDate, Partner partner, Location location, User user) {
        this.active = active;
        this.requestCreationDate = requestCreationDate;
        this.partner = partner;
        this.location = location;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getRequestCreationDate() {
        return requestCreationDate;
    }

    public void setRequestCreationDate(Timestamp requestCreationDate) {
        this.requestCreationDate = requestCreationDate;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

