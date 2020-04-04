package com.example.admin.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.persistence.AdminMemberCustomCrudRepository;
import com.example.domain.Member;
import com.example.domain.MemberRole;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private AdminMemberCustomCrudRepository repo;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test

	public void AdminUserSignUpTests() {
		log.info("Create Admin");
		Member admin = new Member();
		admin.setUserEmail("test@co.kr");
		admin.setUserPassword(passwordEncoder.encode("admin"));
		admin.setUserName("AdminTest");
		MemberRole role = new MemberRole();
		role.setRole("ADMIN");
		admin.setRoles(Arrays.asList(role));
		try {
		repo.save(admin);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		log.info("admin : "+repo.findByUserEmail("admin@co.kr").toString());
	}
	
	@Test
	public void AdminCreateTests() {
		log.info("Create User");
		Member user = new Member();
		/** Update need Idx */
		//user.setIdx(13L);
		user.setUserEmail("test1@co.kr");
		user.setUserPassword(passwordEncoder.encode("test"));
		user.setUserName("Tester");
		MemberRole role = new MemberRole();
		role.setRole("USER");
		user.setRoles(Arrays.asList(role));
		repo.save(user);
	}
	
	@Test
	public void AdminFindByUserEmailTests() {
		log.info("Admin Find By UserEmail Tests");
		Optional<Member> test = repo.findByUserEmail("admin@co.kr");
		log.info("testing : " + test);
	}
	
	@Test
	@Transactional
	public void AdminLoginTests() {
		log.info("Login User");
		String Search = "admin@co.kr";
		List<Member> user = repo.getListByUserEmail(Search);
		log.info("user list :" + user);
		user.forEach(items->{
			if(passwordEncoder.matches("admin",items.getUserPassword())) {				
				log.info("Login:" + items.toString());
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
		if(test1 != null) {
			if(passwordEncoder.matches("test", test1.getUserPassword())) { 
				test1.setUserPassword(passwordEncoder.encode("test"));
				MemberRole role = new MemberRole();
				role.setRole("ADMIN");
				test1.setRoles(Arrays.asList(role));
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
		}else {
			log.info("not user");
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
