package com.example.registration_qbook.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Users {
    @Id
    @GenericGenerator(name= "user_id_seq", strategy = "increment")
    @GeneratedValue(generator="user_id_seq", strategy = GenerationType.AUTO)
    private long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNo;
    private String userName;
    private String gender;
    private java.sql.Date dateOfBirth;
    @Column(columnDefinition="TEXT")
    private String img;
}
