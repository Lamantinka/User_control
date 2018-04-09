package com.example.demo.userConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dao.DaoAccesRepo;
import com.example.demo.entity.Person;

@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	DaoAccesRepo dao;
	@Autowired
	BCryptPasswordEncoder da;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person p = dao.getPersonByLogin(username);
		if (p == null)
			throw new UsernameNotFoundException(username + " not found");
		MyUserDetails myUserDetails = new MyUserDetails(p);
		myUserDetails.setbCryptPasswordEncoder(da);
		return myUserDetails;
	}

}
