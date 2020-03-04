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
		String Search = "admin@co.kr";
		List<Member> user = repo.findByUserEmail(Search);
		log.info("user list :" + user);
		user.forEach(items->{
			if(passwordEncoder.matches("admin",items.getUserPassword())) {				
				log.info("Login:" + items);
			}else {
				log.info("Login Failed");
			}
		});
	}
	
	@Test
	public void AdminRegisteCheckUserTests() {
		log.info("check User");
		Member Search = new Member();
		Search.setUserEmail("admin@co.kr");
		log.info("Set User : " + Search.getUserEmail());
		Member checkEmail =  repo.getMember("admin@co.kr");
		log.info("user is " + checkEmail);
	}
	
	
	@Test
	public void AdminUserFindAllTests() {
		log.info("User Find All");
		repo.findAll().forEach(items->{
			log.info("user : " + items);
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
