package com.example.admin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.admin.persistence.AdminUserRepository;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private AdminUserRepository repo;
	
	@Test
	public void AdminCreateTests() {
		
	}
	
	@Test
	public void AdminLoginTests() {
		
	}
	
	@Test
	public void AdminModifyTests() {
		
	}
	
	@Test
	public void AdminDeleteTests() {
		
	}
	
	
	
}
