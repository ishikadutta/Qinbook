package com.example.registration_qbook.service;

import com.example.registration_qbook.dto.*;

import java.util.List;

public interface RegisterService {
    RegisterResponseDTO registerUser(RegisterRequestDTO userRequestDTO);


    FriendResponseDTO getProfileByUsername(String userName);

    UsersResponseDto getAllDetails(String userName);

    DeleteResponseDTO deleteByUsername(String userName);

    UpdateResponseDTO updateUserByUsername(String userName, UsersRequestDTO usersRequestDTO);

    List<EventResponseDTO> getEventDetails(List<String> userNameList);
}
