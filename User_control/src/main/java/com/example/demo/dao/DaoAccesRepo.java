package com.example.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;

public interface DaoAccesRepo extends CrudRepository<Person, Integer> {
	@Modifying
	@Transactional
	@Query("delete from Person p where p.id = ?1")
	Integer deleteByyId(Integer id);
}
