package com.example.registration_qbook.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jplaceholder", url = "http://10.177.68.66:8082/login")
public interface LoginClient {
    @RequestMapping(method= RequestMethod.POST, path="/insert")
    void insertIntoLogin(@RequestParam("userName") String userName,@RequestParam("password") String password);
}

