package com.example.registration_qbook.repository;

import com.example.registration_qbook.entity.UserDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    @Query(value="Select * from user_details where user_name=?1", nativeQuery = true)
    UserDetails getAllUserDetails(String userName);


    @Query(value="Select * from user_details where user_name=?1", nativeQuery = true)
    UserDetails findByUsername(String userName);

    @Modifying
    @Query(value="Delete from user_details where user_name=?1", nativeQuery = true)
    void deleteByUsername(String userName);

}
