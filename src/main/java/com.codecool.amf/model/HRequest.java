package com.codecool.amf.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
public class HRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean active = true;

    @Transient
    private String creationDate;

    @Column(name = "time_stamp")
    private long timestamp;

    @ManyToOne
    private Partner partner;

    @OneToOne
    private Location location;

    @ManyToOne
    private User user;

    @Transient
    private String locationLabel;


    public HRequest() {
    }

    public HRequest(long timestamp, Partner partner, Location location, User user, String locationLabel) {
        this.locationLabel = locationLabel;
        this.active = true;
        this.timestamp = timestamp;
        this.partner = partner;
        this.location = location;
        this.user = user;
        this.creationDate = convertToDate(timestamp);
    }

    private String convertToDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        return sdf.format(timestamp);
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

    public String getCreationDate() {
        convertToDate(timestamp);
        return creationDate;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public String getLocationLabel() {
        return locationLabel;
    }
}

