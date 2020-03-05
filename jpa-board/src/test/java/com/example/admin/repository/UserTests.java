package com.example.admin.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminUserRepository;
import com.example.domain.Member;
import com.example.domain.MemberRole;

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
	public void AdminUserSignUpTests() {
		log.info("Create Admin");
		Member admin = new Member();
		admin.setUserEmail("admin@co.kr");
		admin.setUserPassword(passwordEncoder.encode("admin"));
		admin.setUserName("AdminTest");
		MemberRole role = new MemberRole();
		role.setRole("ADMIN");
		admin.setRoles(Arrays.asList(role));
		
		repo.save(admin);
		
		log.info("admin : "+repo.findByUserEmail("admin@co.kr").toString());
	}
	
	@Test
	public void AdminCreateTests() {
		log.info("Create User");
		Member user = new Member();
		user.setUserEmail("test@co.kr");
		user.setUserPassword(passwordEncoder.encode("test"));
		user.setUserName("Tester");
		repo.save(user);				
	}
	
	@Test
	public void AdminLoginTests() {
		log.info("Login User");
		String Search = "admin@co.kr";
		List<Member> user = repo.getListByUserEmail(Search);
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
		Member checkEmail =  repo.getMember(Search.getUserEmail());
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
		Member test = new Member();
		Member test1 = new Member();
		test.setUserEmail("test@co.kr");
		log.info("test : " + test);
		test1 = repo.getMember(test.getUserEmail());
		log.info("test get User : " + test1);
		if(passwordEncoder.matches("test", test1.getUserPassword())) { 
			test1.setUserPassword(passwordEncoder.encode("test"));
			repo.save(test1);
		}else {
			log.info("Not Match User Password");
			log.info("get User : " + test1);
			if(passwordEncoder.matches("test", test1.getUserPassword())) {
				log.info("User Change Password Already");
			}else {
				log.info("User Password Not Match");
			}
			
		}
		
	}
	
	@Test
	public void AdminDeleteTests() {
		log.info("Delete User");
		Member test = repo.getMember("test@co.kr");
		if(test != null) {
			
			log.info("Delete user Sample : "+test.getIdx());
			repo.deleteById(test.getIdx());
			log.info("Delete User Do");
			log.info("check delete");
			Member check = repo.getMember("test@co.kr");
			if(check == null) {				
				log.info("Delete");
			}else {
				log.info("Delete Failed...");
			}
		}else {
			log.info("Not Have Sample User");
		}
		
	}
	
	
	
}
