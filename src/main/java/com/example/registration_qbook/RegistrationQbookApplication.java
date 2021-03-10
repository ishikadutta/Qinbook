package com.example.registration_qbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@SpringBootApplication
public class RegistrationQbookApplication {

//	@Bean
//	JedisConnectionFactory jedisConnectionFactory(){
//		return new JedisConnectionFactory();
//	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrationQbookApplication.class, args);
	}

}
