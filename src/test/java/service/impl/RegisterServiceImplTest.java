package service.impl;

import com.example.registration_qbook.dto.*;
import com.example.registration_qbook.entity.UserDetails;
import com.example.registration_qbook.entity.Users;
import com.example.registration_qbook.repository.UserDetailsRepository;
import com.example.registration_qbook.repository.UserRepository;
import com.example.registration_qbook.service.impl.RegisterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
 class RegisterServiceImplTest {

    @InjectMocks
    private RegisterServiceImpl registerService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        ObjectMapper objectMapper = new ObjectMapper();

        Users userObject = null;
        Users userObject2 = null;
        try{
            userObject = objectMapper.readValue(new URL("file:src/test/resources/register.mock"),Users.class);
            userObject2 = objectMapper.readValue(new URL("file:src/test/resources/newRegister.mock"),Users.class);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Mockito.when(userRepository.getAllUsersByUsername("ishika")).thenReturn(userObject);

        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setEmail("ishika@gmail.com");
        registerRequestDTO.setDateOfBirth(new Date(2017-04-21));
        registerRequestDTO.setFirstName("ishika");
        registerRequestDTO.setLastName("dutta");
        registerRequestDTO.setPhoneNo("1234567890");
        registerRequestDTO.setImg("");
        registerRequestDTO.setPassword("abcd");
        registerRequestDTO.setGender("F");
        RegisterResponseDTO responseDTO = registerService.registerUser(registerRequestDTO);

        assertEquals(responseDTO.getMessage(),"Username already Exists");
        assertEquals(responseDTO.getUserName(),null);

        Mockito.verify(userRepository).getAllUsersByUsername("ishika");





    }

    @Test
    void registerUser2() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Users userObject2 = null;

        userObject2 = objectMapper.readValue(new URL("file:src/test/resources/newRegister.mock"),Users.class);


        Mockito.when(userRepository.getAllUsersByUsername("ravi")).thenReturn(userObject2);

        RegisterRequestDTO registerRequestDTO2 = new RegisterRequestDTO();
        registerRequestDTO2.setEmail("ravi@gmail.com");
        registerRequestDTO2.setDateOfBirth(new Date(2010-05-11));
        registerRequestDTO2.setFirstName("ravi");
        registerRequestDTO2.setLastName("shanker");
        registerRequestDTO2.setPhoneNo("1234567890");
        registerRequestDTO2.setImg("");
        registerRequestDTO2.setPassword("abcd");
        registerRequestDTO2.setGender("M");
        RegisterResponseDTO responseDTO2 = registerService.registerUser(registerRequestDTO2);
        assertEquals(responseDTO2.getUserName(),"ravi");
        assertEquals(responseDTO2.getMessage(),"Registration successful");

        Mockito.verify(userRepository).getAllUsersByUsername("ravi");

    }

    @Test
    void getProfileByUsername(){
        ObjectMapper objectMapper = new ObjectMapper();
        Users userMockObject =null;
        try{
            userMockObject = objectMapper.readValue(new URL("file:src/test/resources/register.mock"),Users.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Mockito.when(userRepository.getDetailsByUsername("ishika")).thenReturn(userMockObject);
//        FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
//        friendResponseDTO.setFullName("ishika dutta");
//        friendResponseDTO.setImg("");
        FriendResponseDTO friendResponseDTO = registerService.getProfileByUsername("ishika");

        assertEquals(friendResponseDTO.getFullName(),"ishika dutta");
        assertEquals(friendResponseDTO.getImg(),"");

        Mockito.verify(userRepository).getDetailsByUsername("ishika");

    }

    @Test
    void getAllDetails(){
        ObjectMapper objectMapper = new ObjectMapper();
        Users userMockObject = null;
        UserDetails userDetailsMockObject =null;
        try{
            userMockObject = objectMapper.readValue(new URL("file:src/test/resources/register.mock"), Users.class);
            userDetailsMockObject = objectMapper.readValue(new URL("file:src/test/resources/getProfile.mock"),UserDetails.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Mockito.when(userRepository.getDetailsByUsername("ishika")).thenReturn(userMockObject);
        Mockito.when(userDetailsRepository.getAllUserDetails("ishika")).thenReturn(userDetailsMockObject);
        UsersResponseDto usersResponseDto = registerService.getAllDetails("ishika");

        assertEquals(usersResponseDto.getFirstName(), "ishika");
        assertEquals(usersResponseDto.getLastName(), "dutta");
        assertEquals(usersResponseDto.getUserName(), "ishika");
        assertEquals(usersResponseDto.getUserId(), 1);
        assertEquals(usersResponseDto.getImg(),"");
        assertEquals(usersResponseDto.getRelationshipStatus(),"Single");
        assertEquals(usersResponseDto.getEducation10(), "St. Karen's High School");
        assertEquals(usersResponseDto.getEducation12(), "Baldwin");
        assertEquals(usersResponseDto.getEducationUni(), "Sjce");
        assertEquals(usersResponseDto.getJobProfile(),"intern");
        assertEquals(usersResponseDto.getCompanyName(), "Quinbay");
        assertEquals(usersResponseDto.getJobStartDate(), null);
        assertEquals(usersResponseDto.getJobEndDate(),null);
        assertEquals(usersResponseDto.getYearsOfExp(), 0);
        assertEquals(usersResponseDto.getJobLocation(),"Bangalore");
        assertEquals(usersResponseDto.getAddress(),"");
        assertEquals(usersResponseDto.getMarriageAnniversary(),null);
        assertEquals(usersResponseDto.getHobbies(), "Reading");

    }

    @Test
    void updateUserByUsername(){
        ObjectMapper objectMapper = new ObjectMapper();
        Users userMockObject = null;
        UserDetails userDetailsMockObject =null;
        try{
            userMockObject = objectMapper.readValue(new URL("file:src/test/resources/register.mock"), Users.class);
            userDetailsMockObject = objectMapper.readValue(new URL("file:src/test/resources/getProfile.mock"),UserDetails.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Mockito.when(userRepository.findDetailsByUsername("ishika")).thenReturn(userMockObject);
        Mockito.when(userDetailsRepository.findByUsername("ishika")).thenReturn(userDetailsMockObject);
        UsersRequestDTO usersRequestDTO = new UsersRequestDTO();
        usersRequestDTO.setAddress("dff");
        usersRequestDTO.setDateOfBirth(new Date(2004-03-11));
        usersRequestDTO.setGender("F");
        usersRequestDTO.setImg("");
        usersRequestDTO.setCompanyName("Engati");
        usersRequestDTO.setEducation10("");
        usersRequestDTO.setHobbies("dancing");
        UpdateResponseDTO updateResponseDTO = registerService.updateUserByUsername("ishika",usersRequestDTO);

        assertEquals(updateResponseDTO.getMessage(),"Success");

    }

    @Test
    void getEventDetailsForBirthday() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Users>> userListMockObject = null;
        List<Users> usersList = new ArrayList<>();
        userListMockObject = objectMapper.readValue(new URL("file:src/test/resources/birthday.mock"),ArrayList.class);
        for(Map<String, Users> objectMap: userListMockObject){
            Users users = new Users();
            users.setUserId(Long.parseLong(objectMap.get("userId")+""));
            users.setUserName(String.valueOf(objectMap.get("userName")));
            users.setImg(objectMap.get("img") + "");
            users.setGender(objectMap.get("gender") + "");
           // users.setDateOfBirth(objectMap.get("dateOfBirth"));
            users.setPhoneNo(objectMap.get("phoneNo")+ "");
            users.setFirstName(objectMap.get("firstName")+"");
            users.setLastName(objectMap.get("LastName")+"");
            users.setEmail(objectMap.get("email")+"");

            java.util.Date date = new SimpleDateFormat("YYYY-MM-DD").parse(String.valueOf(objectMap.get("dateOfBirth")));
           users.setDateOfBirth(new Date(date.getTime()) );
            usersList.add(users);
        }

        Mockito.when(userRepository.findUsersWithBirthday()).thenReturn(usersList);

        Users userMockObject = null;
        Users userMockObject1 = null;


            userMockObject = objectMapper.readValue(new URL("file:src/test/resources/details.mock"), Users.class);
        userMockObject1 = objectMapper.readValue(new URL("file:src/test/resources/details2.mock"), Users.class);
            Mockito.when(userRepository.findDetailsByUsername("ishika1")).thenReturn(userMockObject);
        Mockito.when(userRepository.findDetailsByUsername("ishika2")).thenReturn(userMockObject1);

        List<String> list = new ArrayList<>();
        list.add("ishika1");
        list.add("ishika2");

        List<EventResponseDTO> eventResponseDTO = registerService.getEventDetails(list);
        assertEquals(eventResponseDTO.get(0).getEventType()," Birthday");
        assertEquals(eventResponseDTO.get(0).getImg(),"");
        assertEquals(eventResponseDTO.get(0).getUserName(),"ishika1");
        assertEquals(eventResponseDTO.get(0).getFullName(),"ishika1 dutta1");

        assertEquals(eventResponseDTO.get(1).getEventType()," Birthday");
        assertEquals(eventResponseDTO.get(1).getImg(),"");
        assertEquals(eventResponseDTO.get(1).getUserName(),"ishika2");
        assertEquals(eventResponseDTO.get(1).getFullName(),"ishika2 dutta2");


    }

}
