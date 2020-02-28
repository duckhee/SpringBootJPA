package com.example.admin.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminUserRepository;
import com.example.domain.Member;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private AdminUserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void AdminCreateTests() {
		log.info("Create User");
		Member user = new Member();
		user.setUserEmail("admin@co.kr");
		user.setUserPassword(passwordEncoder.encode("admin"));
		user.setUserName("adminTest");
		repo.save(user);				
	}
	
	@Test
	public void AdminLoginTests() {
		log.info("Login User");
		List<Member> user = repo.findByUserEmail("admin@co.kr");
		user.forEach(items->{
			if(passwordEncoder.matches("admin",items.getUserPassword())) {				
				log.info("Login:" + items);
			}else {
				log.info("Login Failed");
			}
		});
	}
	
	@Test
	public void AdminModifyTests() {
		log.info("Update User");
		
	}
	
	@Test
	public void AdminDeleteTests() {
		log.info("Delete User");
	}
	
	
	
}
