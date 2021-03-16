package com.example.registration_qbook.repository;

import com.example.registration_qbook.entity.UserNameandImgDTo;
import com.example.registration_qbook.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<Users,Long> {
    @Query(value= "Select * from users where email = ?1", nativeQuery = true)
    Users getAllUsersByEmail(String email);

    @Query(value= "Select * from users where user_name=?1", nativeQuery = true)
    Users getDetailsByUsername(String userName);

    @Modifying
    @Query(value = "delete from users where user_name=?1", nativeQuery = true)
    void deleteByUsername(String userName);

    @Query(value = "select * from users where user_name=?1",nativeQuery = true)
    Users findDetailsByUsername(String userName);

    @Query(value="Select * from users where DATE_PART('day',date_of_birth) = date_part('day',CURRENT_DATE) AND DATE_PART('month',date_of_birth) = date_part('month',CURRENT_DATE)", nativeQuery = true)
    List<Users> findUsersWithBirthday();

    @Query(value="SELECT AGE(CURRENT_DATE,date_of_birth) from users where user_name=?1", nativeQuery = true)
    int findAge(String userName);

    @Query(value= "Select * from users where user_name = ?1", nativeQuery = true)
    Users getAllUsersByUsername(String s);
}
