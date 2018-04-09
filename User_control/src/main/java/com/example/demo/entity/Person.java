package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.core.annotation.Order;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;
	@Size(min = 2, message = "size must be greater then 2")
	@Column(name = "Name")
	private String name = "";

	@Column(name = "Surname")
	@Order(2)
	private String surname = "";

	@Column(name = "Age")
	private Integer age = 0;

	@Column(name = "Role")
	private String role = "";

	@Column(name = "Login", unique = true)
	private String login = "";

	@Column(name = "Password")
	private String password = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		// System.out.println("getter log " + login);
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		// System.out.println("getter pass " + password);

		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", role=" + role + ", login=" + login + ", password=" + password + "]";
	}

}
