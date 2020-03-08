package com.example.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.admin.persistence.AdminBoardRepository;
import com.example.domain.Boards;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/boards")
public class AdminBoardController {
	
	@Autowired
	private AdminBoardRepository repo;
	
	@GetMapping(value= {"","/"})
	public String MainPage() {
		log.info("Admin Board Main Redirect");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage(@ModelAttribute("vo") Boards board, Model models, HttpServletRequest req) {
		log.info("Admin Board Create Page");
		log.info("get Session :" + req.getSession());
		models.addAttribute("writer", req.getSession());
		return "admin/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo(@ModelAttribute("vo") Boards board, RedirectAttributes redirectFlash) {
		log.info("Admin Board Create Do");
		log.info("board Value : " + board);
		if(board.getTitle() == null) {
			redirectFlash.addFlashAttribute("msg", "Title Need");
			return "redirect:/admin/boards/create";
		}
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage(Model model) {
		log.info("Admin Board List Page");
		return "admin/board/list";
	}
	
	@GetMapping(value="/update")
	public String ModifyPage(@ModelAttribute("vo") Boards board) {
		log.info("Admin Board Modify Page");
		return "admin/board/update";
	}
	
	@PostMapping(value="/update")
	public String ModifyDo(@ModelAttribute("vo") Boards board) {
		log.info("Admin Board Modify Do");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage() {
		log.info("Admin Board View Page");
		return "admin/board/view";
	}

}
