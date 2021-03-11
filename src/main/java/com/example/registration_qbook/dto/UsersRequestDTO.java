package com.example.registration_qbook.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UsersRequestDTO {
//    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private String userName;
    private String gender;
    private java.sql.Date dateOfBirth;
    private String img;
    private String address;
    private String relationshipStatus;
    private String education10;
    private String education12;
    private String educationUni;
    private String jobProfile;
    private String companyName;
    private java.sql.Date jobStartDate;
    private java.sql.Date jobEndDate;
    private int yearsOfExp;
    private String jobLocation;
    private java.sql.Date marriageAnniversary;

}
