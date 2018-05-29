package com.codecool.amf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String name;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private Address address;
    @Column(unique = true,name = "id_card")
    private String idCardNum;
    @OneToMany(mappedBy = "user")
    private List<HRequest> requests = new ArrayList<>();
}
