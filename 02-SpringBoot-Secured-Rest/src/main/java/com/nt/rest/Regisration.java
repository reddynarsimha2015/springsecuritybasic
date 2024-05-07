package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.MyUser;
import com.nt.repository.MyUserRepository;

@RestController
public class Regisration {

	@Autowired
	MyUserRepository rep;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/register/user")
	public MyUser create(@RequestBody MyUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		rep.save(user);
		return user;
	}
}
