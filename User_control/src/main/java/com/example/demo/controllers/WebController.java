package com.example.demo.controllers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	public static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private JpaService service;

	@GetMapping("/allPersons")
	public ModelAndView allPersons(HttpSession session) {
		SecurityContextImpl s = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Collection<? extends GrantedAuthority> authorities = s.getAuthentication().getAuthorities();
		List<String> l = new LinkedList<String>();
		for (GrantedAuthority grantedAuthority : authorities) {
			l.add(grantedAuthority.getAuthority());

		}
		if (!l.contains("ROLE_ADMIN"))
			return new ModelAndView("not_allowed");

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

	@GetMapping(value = "/login")
	public String LoginPage() {
		return "login";
	}

	// Post-redirect-get
	@PostMapping("/deletePerson")
	public String deletePerson(@RequestParam("id_delete") Integer id) {
		System.out.println(id);
		service.deletePersonById(id);

		return "redirect:/web/allPersons";
	}
}
