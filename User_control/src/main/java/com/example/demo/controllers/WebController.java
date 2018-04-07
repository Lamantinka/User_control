package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		Iterable<Person> all = service.selectAndGroupById();
		m.addObject("persons", all);
		m.addObject("new_person", new Person());
		m.addObject("update_person", new Person());

		return m;
	}

	// Post-redirect-get
	@PostMapping("/addNewPerson")
	public String addNewPerson(@Valid @ModelAttribute("new_person") Person p, BindingResult br, Model model) {
		if (br.hasErrors()) {
			System.out.println("error");
			Iterable<Person> all = service.selectAndGroupById();
			model.addAttribute("persons", all);
			model.addAttribute("update_person", new Person());

			return "show_all";
		}
		service.save(p);

		return "redirect:/web/allPersons";
	}

	// Post-redirect-get
	@PostMapping("/updateExistingPerson")
	public String updateExistingPerson(@Valid @ModelAttribute("update_person") Person p, BindingResult br, Model model) {
		if (br.hasErrors()) {
			System.out.println("error");
			Iterable<Person> all = service.selectAndGroupById();
			model.addAttribute("persons", all);
			model.addAttribute("new_person", new Person());
			return "show_all";
		}
		service.updatePerson(p);

		return "redirect:/web/allPersons";
	}

	// Post-redirect-get
	@PostMapping("/deletePerson")
	public String deletePerson(@RequestParam("id_delete") Integer id) {
		System.out.println(id);
		service.deletePersonById(id);

		return "redirect:/web/allPersons";
	}
}
