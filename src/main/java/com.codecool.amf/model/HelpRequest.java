package com.codecool.amf.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "help_request")
public class HelpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean active = true;

    private String creationDate;

    @Column(name = "time_stamp")
    private long timestamp;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Partner partner;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private String locationLabel;


    public HelpRequest() {
    }

    public HelpRequest(long timestamp, Partner partner, Location location, User user, String locationLabel) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public Partner getPartner() {
        return partner;
    }

    public User getUser() {
        return user;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public long getId() {
        return id;
    }
}

