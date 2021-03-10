package com.example.registration_qbook.service.impl;

import com.example.registration_qbook.clients.LoginClient;
import com.example.registration_qbook.dto.*;
import com.example.registration_qbook.entity.UserDetails;
import com.example.registration_qbook.entity.Users;
import com.example.registration_qbook.repository.UserDetailsRepository;
import com.example.registration_qbook.repository.UserRepository;
import com.example.registration_qbook.service.RegisterService;
import com.example.registration_qbook.util.CustomHash;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class RegisterServiceImpl implements RegisterService {

    public static Properties getPropertiesOfKafka(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private LoginClient loginClient;

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
            String hashedpassword = CustomHash.hashString(userRequestDTO.getPassword());
            hashedpassword = CustomHash.hashString(hashedpassword);
            System.out.println(hashedpassword);
            // String password = userRequestDTO.getPassword();
            // users.setPassword(password);
            String[] username = userRequestDTO.getEmail().split("@",-1);
            loginClient.insertIntoLogin(username[0],hashedpassword);
            userDetails.setUserName(username[0]);
            users.setUserName(username[0]);
            Users savedUsers = userRepository.save(users);
            UserDetails savedUsers2 = userDetailsRepository.save(userDetails);
            responseDTO.setMessage("Registration successful");
            kafkaMethod("message");
        }
        return responseDTO;
    }
    private void kafkaMethod(String message){
        Producer<String,String> producer = new KafkaProducer<>(getPropertiesOfKafka());
        producer.send(new ProducerRecord<>("my-events",message));
        producer.close();
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
        BeanUtils.copyProperties(usersRequestDTO, userDetailsFromDb);
        BeanUtils.copyProperties(usersRequestDTO,usersFromDb);
        userDetailsRepository.save(userDetailsFromDb);
        userRepository.save(usersFromDb);
        UpdateResponseDTO updateResponseDto = new UpdateResponseDTO();
        updateResponseDto.setMessage("Success");
        return updateResponseDto;

    }
}
