package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Person;
import com.example.demo.services.JpaService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	@Autowired
	private JpaService service;

	@GetMapping("/getById/{id}")
	public Person getById(@PathVariable("id") Integer id) {
		return service.getById(id);
	}

	@GetMapping("/getAll")
	public Iterable<Person> getAll() {
		return service.getAll();
	}

	@PutMapping("/save")
	public Person save(@RequestBody Person p) {
		return service.save(p);
	}

	@PutMapping("/updatePersonNameById/{id}/{new_name}")
	public Person updatePersonNameById(@PathVariable Integer id, @PathVariable String new_name) {
		return service.updatePersonNameById(id, new_name);
	}

	@DeleteMapping("/deletePersonById/{id}")
	public Integer deletePersonById(@PathVariable Integer id) {
		return service.deletePersonById(id);
	}
}
