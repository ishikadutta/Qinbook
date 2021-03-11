package com.example.registration_qbook.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private String gender;
    private java.sql.Date dateOfBirth;
    private String img;
//    private String img;
//    private String relationshipStatus;
//    private String accountType;
//    private String education10;
//    private String education12;
//    private String educationUni;
//    private String jobProfile;
//    private String companyName;
//    private String jobStartDate;
//    private String jobEndDate;
//    private int yearsOfExp;
//    private String jobLocation;

}
