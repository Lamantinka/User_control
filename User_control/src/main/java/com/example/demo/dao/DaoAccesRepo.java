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
	Integer deletePersonById(Integer id);

	@Query("select p from Person p group by p.id")
	Iterable<Person> selectAndGroupById();

	@Query("select p.name from Person p where id = ?1")
	String getNameById(Integer id);

	@Query("select p.surname from Person p where id = ?1")
	String getSurnameById(Integer id);

	@Query("select p.email from Person p where id = ?1")
	String getEmailById(Integer id);

	@Query("select p.age from Person p where id = ?1")
	String getAgeById(Integer id);

	@Query("select p.login from Person p where id = ?1")
	String getLoginById(Integer id);

	@Query("select p.password from Person p where id = ?1")
	String getPasswordById(Integer id);

	@Modifying
	@Transactional
	@Query("update Person p set p.name = ?2, p.surname = ?3, p.age = ?4, p.email = ?5, p.login = ?6, p.password = ?7 where id = ?1")
	void updateById(Integer id, String name, String surname, int age, String email, String login, String password);

}
