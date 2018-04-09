package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.services.JpaService;

@SpringBootApplication
public class PersonControlApplication implements CommandLineRunner {
	public static final Logger logger = LoggerFactory.getLogger(PersonControlApplication.class);
	@Autowired
	private JpaService accesRepo;

	public static void main(String[] args) {
		SpringApplication.run(PersonControlApplication.class, args);

	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
