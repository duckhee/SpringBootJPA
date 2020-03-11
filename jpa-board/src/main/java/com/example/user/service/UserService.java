package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.persistence.AdminUserRepository;

import lombok.extern.java.Log;

@Log
@Service
public class UserService implements UserDetailsService{

	@Autowired
	private AdminUserRepository repo;
	/**
	 * need to @Transactional
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		/** Try Catch */
		try {
			log.info("User Detail Get Start");
			return repo.findByUserEmail(username).filter(user->user != null).map(user->new SecurityUser(user)).get();
		}catch (UsernameNotFoundException notFound) {
			// TODO: handle exception
			log.info("Not found Error");
			notFound.printStackTrace();
			return null;
		}
	}

}
