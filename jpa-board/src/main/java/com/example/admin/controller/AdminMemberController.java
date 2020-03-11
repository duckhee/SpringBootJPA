package com.example.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String CreatePage(@ModelAttribute("user")Member user, RedirectAttributes flash) {
		log.info("Member Create Page");
		return "admin/member/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo(@ModelAttribute("user")Member user) {
		log.info("Member Create Do");
		log.info("user : " + user);
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/update")
	public String ModifyPage(Model model, HttpServletRequest request, RedirectAttributes flash) {
		log.info("Member Modify Page");
		String email = request.getParameter("id");
		log.info("Update User Email : " + email);
		if(email == null) {
			/** Not Email get Parameter */
			flash.addAttribute("", "");
			return "redirect:/admin/users/login";
		}
		return "admin/member/update";
	}
	
	@PostMapping(value="/update")
	public String ModifyDo(Model model, HttpServletRequest request, RedirectAttributes flash) {
		log.info("Member Modify Do");
		/** TODO view page mapping */
		return "redirect:/admin/members/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage(HttpServletRequest request, Model model, RedirectAttributes flash) {
		log.info("Member Detail");
		String Email = request.getParameter("id");
		log.info("email : "+request.getParameter("id"));
		if(Email == null) {
			/** Failed Flash Message */
			flash.addFlashAttribute("", "");
			return "redirect:/admin/members/list";
		}
		/** Member Idx, Member UserName, Member UserEmail, MemberRole role Member CreatedAt, Member updatedAt */
		Object[] users = repo.getDetail(Email);
		log.info("member:"+users);
		model.addAttribute("userInfo",users);
		return "admin/member/view";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo(HttpServletRequest request, RedirectAttributes flash) {
		log.info("Member Delete Do");
		return "redirect:/admin/members/list";
	}
}
