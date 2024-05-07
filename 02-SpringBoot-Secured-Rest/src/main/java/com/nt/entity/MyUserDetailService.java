package com.nt.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.repository.MyUserRepository;
@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	MyUserRepository rep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<MyUser> user = rep.findByUsername(username);
		if(user.isPresent()) {
		var userObj=user.get();
		return User.builder().username(userObj.getUsername())
    	    	.password(userObj.getPassword())
    	    	.roles(getRoles(userObj))
    	    	.build();
		}
		else {
			throw new UsernameNotFoundException(username);
		}
	
	}

	private String[] getRoles(MyUser userObj) {
		// TODO Auto-generated method stub
		if(userObj.getRole()==null)
			
		return new String[] {"USER"};
		else
			return userObj.getRole().split(",");
	}

}
