package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.admin.persistence.AdminUserRepository;

import lombok.extern.java.Log;

@Log
@Service
public class AdminUserService implements UserDetailsService{

	@Autowired
	private AdminUserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		/** Try Catch */
		try {
			return repo.findByUserEmail(username).filter(user->user != null).map(user->new AdminSecurityUser(user)).get();
		}catch (UsernameNotFoundException notFound) {
			// TODO: handle exception
			log.info("Not found Error");
			notFound.printStackTrace();
			return null;
		}
	}

}
