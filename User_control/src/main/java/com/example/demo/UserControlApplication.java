package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.DaoAccesRepo;

@SpringBootApplication
public class UserControlApplication implements CommandLineRunner {
	@Autowired
	private DaoAccesRepo dao;

	public static void main(String[] args) {
		SpringApplication.run(UserControlApplication.class, args);
		System.out.println(999999999);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
