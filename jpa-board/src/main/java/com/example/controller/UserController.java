package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@RequestMapping(value="/users")
public class UserController {

	@GetMapping(value="")
	public String MainPage() {
		log.info("User Main Page");
		return "redriect:/users/login";
	}
	
	@GetMapping(value="/login")
	public String LoginPage() {
		log.info("User Login Page");
		return "/customer/user/login";
	}
	
	@PostMapping(value="/login")
	public String LoginDo() {
		log.info("User Login Do");
		return "";
	}
	
	@GetMapping(value="/profile")
	public String ProfilePage() {
		log.info("User Profile");
		return "";
	}
	
	@GetMapping(value="/modify")
	public String ModifyPage() {
		log.info("User Modify Page");
		return "";
	}
	
	@PostMapping(value="/modify")
	public String ModifyDo() {
		log.info("User Modify");
		return "";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo() {
		log.info("Delete User");
		return "";
	}
	
	@PostMapping(value="/logout")
	public String LogoutDo() {
		log.info("Logout User");
		return "";
	}
}
