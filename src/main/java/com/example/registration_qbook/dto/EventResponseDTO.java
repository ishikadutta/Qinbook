package com.example.registration_qbook.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventResponseDTO {
    private String eventType;
    private int years;
    private String userName;
    private String fullName;
    private String img;

}
