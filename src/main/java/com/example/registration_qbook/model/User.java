package com.example.registration_qbook.model;

import lombok.Data;

@Data
public class User {
    private String userName;
    private int age;
    private String message;

    public User(String userName, int age, String message) {
        this.userName = userName;
        this.age = age;
        this.message = message;
    }
}
