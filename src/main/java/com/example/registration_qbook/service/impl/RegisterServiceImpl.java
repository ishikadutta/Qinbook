package com.example.registration_qbook.service.impl;

import com.example.registration_qbook.dto.*;
import com.example.registration_qbook.entity.UserDetails;
import com.example.registration_qbook.entity.Users;
import com.example.registration_qbook.repository.UserDetailsRepository;
import com.example.registration_qbook.repository.UserRepository;
import com.example.registration_qbook.service.RegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO userRequestDTO) {
    Users users = new Users();
    UserDetails userDetails = new UserDetails();
        System.out.println(userRequestDTO);
    RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        Users emailList =  userRepository.getAllUsersByEmail(userRequestDTO.getEmail());
        if(emailList!=null){
            responseDTO.setMessage("Email already exists");
        }
        else{
            BeanUtils.copyProperties(userRequestDTO, users);
            String password = userRequestDTO.getPassword();
            users.setPassword(password);
            String[] username = userRequestDTO.getEmail().split("@",-1);
            userDetails.setUserName(username[0]);
            users.setUserName(username[0]);
            Users savedUsers = userRepository.save(users);
            responseDTO.setMessage("Registration successful");
        }
        return responseDTO;
    }

    @Override
    public FriendResponseDTO getProfileByUsername(String userName) {
        Users users = new Users();
       // UsersResponseDto responseDTO = new UsersResponseDto();
        FriendResponseDTO response = new FriendResponseDTO();
        Users userDetails = userRepository.getDetailsByUsername(userName);
        System.out.println(userDetails);
       // responseDTO.GetProfileDetails()
        //BeanUtils.copyProperties(userDetails,responseDTO);
        response.setImg(userDetails.getImg());
        response.setFullName(userDetails.getFirstName()+" "+userDetails.getLastName());
        return response;
    }

    @Override
    public UsersResponseDto getAllDetails(String userName) {
        Users users = new Users();
        UsersResponseDto responseDto = new UsersResponseDto();
        Users registerDetails = userRepository.getDetailsByUsername(userName);
        System.out.println(registerDetails);
        BeanUtils.copyProperties(registerDetails,responseDto);
        UserDetails allDetails = userDetailsRepository.getAllUserDetails(userName);
        System.out.println(allDetails);
        BeanUtils.copyProperties(allDetails,responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public DeleteResponseDTO deleteByUsername(String userName) {

        UserDetails userDetails = userDetailsRepository.findByUsername(userName);
        System.out.println(userDetails);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        if(userDetails!=null){
            userDetailsRepository.deleteByUsername(userName);
            userRepository.deleteByUsername(userName);
            deleteResponseDTO.setMessage("Your Account has been Deleted");
            return deleteResponseDTO;
        }
        return null;
    }

    @Override
    public UpdateResponseDTO updateUserByUsername(String userName, UsersRequestDTO usersRequestDTO) {

        Users users = new Users();
        UserDetails userDetails = new UserDetails();
        UserDetails userDetailsFromDb = userDetailsRepository.findByUsername(userName);
        Users usersFromDb = userRepository.findDetailsByUsername(userName);
        System.out.println(userDetailsFromDb);
        System.out.println(usersFromDb);
//        userDetailsFromDb.setUserName(usersRequestDTO.getUserName());
//        userDetailsFromDb.setAddress(usersRequestDTO.getAddress());
//        userDetailsFromDb.setEducation10(usersRequestDTO.getEducation10());
//        userDetailsFromDb.setEducation12(usersRequestDTO.getEducation12());
//        userDetailsFromDb.setEducationUni(usersRequestDTO.getEducationUni());
//        userDetailsFromDb.setJobProfile(usersRequestDTO.getJobProfile());
//        userDetailsFromDb.setCompanyName(usersRequestDTO.getCompanyName());
//        userDetailsFromDb.setJobLocation(usersRequestDTO.getJobLocation());
//        userDetailsFromDb.setJobStartDate(usersRequestDTO.getJobStartDate());
//        userDetailsFromDb.setJobEndDate(usersRequestDTO.getJobEndDate());
//        userDetailsFromDb.setRelationshipStatus(usersRequestDTO.getRelationshipStatus());
//        usersFromDb.setUserName(usersRequestDTO.getUserName());
//        usersFromDb.setEmail(usersRequestDTO.getEmail());
//        usersFromDb.setFirstName(usersRequestDTO.getFirstName());
//        usersFromDb.setLastName(usersRequestDTO.getLastName());
//        usersFromDb.setImg(usersRequestDTO.getImg());
//        usersFromDb.setPassword(usersRequestDTO.getPassword());
//        usersFromDb.setGender(usersRequestDTO.getGender());
//        usersFromDb.setPhoneNo(usersRequestDTO.getPhoneNo());
//        usersFromDb.setDateOfBirth(usersRequestDTO.getDateOfBirth());
        BeanUtils.copyProperties(usersRequestDTO, userDetailsFromDb);
        BeanUtils.copyProperties(usersRequestDTO,usersFromDb);
        userDetailsRepository.save(userDetailsFromDb);
        userRepository.save(usersFromDb);
        UpdateResponseDTO updateResponseDto = new UpdateResponseDTO();
        updateResponseDto.setMessage("Success");
        return updateResponseDto;

    }
}
