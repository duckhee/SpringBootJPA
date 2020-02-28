package com.example.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/boards")
public class AdminBoardController {
	
	@GetMapping(value="")
	public String MainPage() {
		log.info("Admin Board Main Redirect");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage() {
		log.info("Admin Board Create Page");
		return "admin/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo() {
		log.info("Admin Board Create Do");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage() {
		log.info("Admin Board List Page");
		return "admin/board/list";
	}
	
	@GetMapping(value="/update")
	public String ModifyPage() {
		log.info("Admin Board Modify Page");
		return "admin/board/update";
	}
	
	@PostMapping(value="/update")
	public String ModifyDo() {
		log.info("Admin Board Modify Do");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage() {
		log.info("Admin Board View Page");
		return "admin/board/view";
	}

}
