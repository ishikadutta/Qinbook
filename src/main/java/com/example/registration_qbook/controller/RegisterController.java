package com.example.registration_qbook.controller;

import com.example.registration_qbook.dto.*;
import com.example.registration_qbook.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class RegisterController {

//    @Autowired
//    private KafkaTemplate<Object, User> kafkaTemplate;
//
//    private static final String TOPIC="my-events";
//
//    @GetMapping("/publish/userName")
//    public String post(@RequestParam("userName") final String userName){
//        kafkaTemplate.send(TOPIC, new User(userName, 12 ,"sa"));
//        return "puclisedd";
//    }


    @Autowired
    private RegisterService registerService;


    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return registerService.registerUser(registerRequestDTO);
    }
    @GetMapping("/userName")
    public FriendResponseDTO getProfileByUsername(@RequestParam String userName){
        return registerService.getProfileByUsername(userName);
    }
    @GetMapping("/getDetails/userName")
    public UsersResponseDto getAllDetails(@RequestParam String userName){
        return registerService.getAllDetails(userName);
    }
    @DeleteMapping("/delete/userName")
    public DeleteResponseDTO deleteByUsername(@RequestParam String userName){
        return registerService.deleteByUsername(userName);
    }

    @PutMapping("/update/userName")
    public UpdateResponseDTO updateUserByUsername(@RequestParam String userName , @RequestBody UsersRequestDTO usersRequestDTO){
        return registerService.updateUserByUsername(userName,usersRequestDTO);
    }

    @GetMapping("/events")
    public List<EventResponseDTO> getEventDetails(@RequestParam List<String> userNameList)
    {
        return registerService.getEventDetails(userNameList);
    }



}
