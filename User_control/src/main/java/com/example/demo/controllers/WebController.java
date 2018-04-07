package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Person;
import com.example.demo.services.JpaService;

@Controller
@RequestMapping("/web")
public class WebController {
	@Autowired
	private JpaService service;

	@RequestMapping("/allPersons")
	public ModelAndView allPersons() {
		ModelAndView m = new ModelAndView("show_all");
		Iterable<Person> all = service.getAll();
		m.addObject("persons", all);
		return m;
	}

}
