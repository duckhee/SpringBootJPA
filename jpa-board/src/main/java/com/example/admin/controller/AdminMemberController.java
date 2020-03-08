package com.example.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/members")
public class AdminMemberController {

	@GetMapping(value= {"","/"})
	public String Main() {
		log.info("Redirect List Page");
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage(Model model) {
		log.info("Member List Page");
		return "admin/board/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage(@ModelAttribute("vo")Member user) {
		log.info("Member Create Page");
		return "admin/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo(@ModelAttribute("vo")Member user) {
		log.info("Member Create Do");
		log.info("user : " + user);
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/update")
	public String ModifyPage() {
		log.info("Member Modify Page");
		return "admin/board/update";
	}
	
	@PostMapping(value="/update")
	public String ModifyDo() {
		log.info("Member Modify Do");
		/** TODO view page mapping */
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage() {
		log.info("Member Detail");
		return "admin/board/view";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo() {
		log.info("Member Delete Do");
		return "redirect:/admin/members/list";
	}
}
