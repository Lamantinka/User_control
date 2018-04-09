package com.example.demo.userConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Person;

@Component
public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 2549547690657111195L;

	public BCryptPasswordEncoder bCryptPasswordEncoder;

	private Person person;

	public MyUserDetails() {

	}

	public MyUserDetails(Person p) {
		this.person = p;
	}

	@Override
	public String getPassword() {

		return bCryptPasswordEncoder.encode(person.getPassword());

	}

	@Override
	public String getUsername() {
		return person.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> l = new ArrayList<>();

		l.add(new SimpleGrantedAuthority(person.getRole()));
		return l;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

}
