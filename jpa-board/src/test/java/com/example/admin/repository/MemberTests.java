package com.example.admin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminMemberRepository;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTests {

	@Autowired
	private AdminMemberRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void InsertListMemberTests() {
		
	}
	
	@Test
	public void ListMemberTests() {
		
	}
	
	@Test
	public void UpdateMemberTests() {
		
	}
	
	@Test
	public void DeleteMemberTests() {
		
	}
}
