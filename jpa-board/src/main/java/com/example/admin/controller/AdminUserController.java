package com.example.admin.controller;

import java.security.Principal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

//import java.security.Principal;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.admin.persistence.AdminMemberCustomCrudRepository;
import com.example.domain.Member;
//import com.example.persistence.UserRepository;
import com.example.domain.MemberRole;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/users")
public class AdminUserController {
	
	@Autowired
	private AdminMemberCustomCrudRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value= {"","/"})
	public String MainPage() {
		log.info("User Main Page");
		return "redirect:/admin/users/profile";
	}
	
	@GetMapping(value="/signup")
	public String RegistePage(@ModelAttribute("user")Member user) {
		log.info("User Registe Page");
		return "admin/user/registe";
	}
	
	@PostMapping(value="/signup")
	public String RegisteDo(@ModelAttribute("user")Member user) {
		log.info("User Registe Do");
		/** Password Make Encoding Set */
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		log.info("Insert User : " + user);
		Member CheckEmail = repo.getMember(user.getUserEmail());
		log.info("Find User : " + CheckEmail);
		if(CheckEmail == null) {
			/** User Save Repository */
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			/** Member Role Default USER */
			MemberRole roles = new MemberRole();
			roles.setRole("USER");
			user.setRoles(Arrays.asList(roles));
			repo.save(user);
			return "redirect:/admin/users/login";
		}else {
			log.info("Find Email : " + CheckEmail);
			return "redirect:/admin/users/registe";
		}
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
		return "redirect:/admin/users/login";
	}
	
	@GetMapping(value="/profile")
	public String ProfilePage(HttpServletRequest request) {
		log.info("User Profile Page");
		Principal principal = request.getUserPrincipal();
		System.out.println("principal : " + principal);
		/** view get [[${#authentication.principal.user}]] is Member Vo */
		return "admin/user/profile";
	}
	
	@GetMapping(value="/modify")
	public String ModifyPage(@ModelAttribute("user")Member user, HttpServletRequest request) {
		log.info("User Modify Page");
		return "admin/user/modify";
	}
	
	@PostMapping(value="/modify")
	public String ModifyDo(@ModelAttribute("user")Member user, HttpServletRequest request, RedirectAttributes flash) {
		log.info("User Modify Do");
		return "redirect:/admin/users/profile";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo() {
		log.info("User Delete");
		return "redirect:/admin/users/signup";
	}
}
