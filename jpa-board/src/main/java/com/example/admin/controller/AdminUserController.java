package com.example.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/users")
public class AdminUserController {
	
	@GetMapping(value="")
	public String MainPage() {
		log.info("User Main Page");
		return "redirect:/admin/users/profile";
	}
	
	@GetMapping(value="/registe")
	public String RegistePage(@ModelAttribute("vo")Member user) {
		log.info("User Registe Page");
		return "admin/user/registe";
	}
	
	@PostMapping(value="/registe")
	public String RegisteDo() {
		log.info("User Registe Do");
		return "redirect:/admin/usersh/login";
	}
	
	@GetMapping(value="/login")
	public String LoginPage() {
		log.info("User Login Page");
		return "admin/user/login";
	}
	
	@PostMapping(value="/login")
	public String LoginDo() {
		log.info("User Login Do");
		return "redirect:/admin";
	}
	
	@PostMapping(value="/logout")
	public String LogoutDo() {
		log.info("User Log out Do");
		return "";
	}
	
	@GetMapping(value="/profile")
	public String ProfilePage() {
		log.info("User Profile Page");
		return "admin/user/profile";
	}
	
	@GetMapping(value="/modify")
	public String ModifyPage() {
		log.info("User Modify Page");
		return "admin/user/modify";
	}
	
	@PostMapping(value="/modify")
	public String ModifyDo() {
		log.info("User Modify Do");
		return "redirect:/admin/users/profile";
	}
}
