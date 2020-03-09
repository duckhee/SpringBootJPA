package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.admin.persistence.AdminMemberCustomCrudRepository;
import com.example.domain.Member;
import com.example.util.PageMaker;
import com.example.util.PageVo;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/members")
public class AdminMemberController {
	
	@Autowired
	private AdminMemberCustomCrudRepository repo;

	@GetMapping(value= {"","/"})
	public String Main() {
		log.info("Redirect List Page");
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage(Model model, @ModelAttribute("pageVO")PageVo page) {
		log.info("Member List Page");
		Pageable paging = page.makePageable(0, "idx");
		Page<Object[]> result = repo.getPaging(page.getType(), page.getKeyword(), paging);
		log.info("page info : " + paging);
		log.info("list info : " + result);
		model.addAttribute("pageList", new PageMaker<>(result));
		return "admin/member/list";
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
