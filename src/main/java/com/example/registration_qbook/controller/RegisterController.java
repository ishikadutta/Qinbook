package com.example.registration_qbook.controller;

import com.example.registration_qbook.dto.*;
import com.example.registration_qbook.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RegisterController {
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


}
