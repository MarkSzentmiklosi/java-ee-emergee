package main.java.com.codecool.amf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class HRequest {
    @Id
    private int id;
    private int userId;
    private Timestamp requestCreationDate;
    private boolean active = true;
    private int partnerId;
    private String location;

}

