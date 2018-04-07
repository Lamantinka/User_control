package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoAccesRepo;
import com.example.demo.entity.Person;

/*
 * All methods returns null if Person does not exist. 
 */

@Service
public class JpaService {
	@Autowired
	DaoAccesRepo accesRepo;

	// Update Person if already exist
	public Person save(Person p) {
		return accesRepo.save(p);
	}

	public Person getById(Integer id) {
		Optional<Person> optional = accesRepo.findById(id);

		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	public Person updatePersonNameById(Integer id, String new_name) {
		Optional<Person> optional = accesRepo.findById(id);
		if (!optional.isPresent())
			return null;

		Person p = optional.get();
		p.setName(new_name);
		return accesRepo.save(p);

	}

	public Integer deletePersonById(Integer id) {

		return accesRepo.deleteByyId(id);
	}

	public Iterable<Person> getAll() {
		return accesRepo.findAll();
	}
}
