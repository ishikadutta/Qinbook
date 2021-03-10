package com.example.registration_qbook.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class UserDetails {
    @Id
    @GenericGenerator(name= "user_id_seq", strategy = "increment")
    @GeneratedValue(generator="user_id_seq", strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String img;
    private String relationshipStatus;
    private String education10;
    private String education12;
    private String educationUni;
    private String jobProfile;
    private String companyName;
    private java.sql.Date jobStartDate;
    private java.sql.Date jobEndDate;
    @Column(nullable= true)
    private Long yearsOfExp;
    private String jobLocation;
    private String address;

}
