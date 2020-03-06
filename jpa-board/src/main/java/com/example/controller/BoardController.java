package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/boards")
public class BoardController {

	@GetMapping(value= {"", "/"})
	public String MainPage() {
		log.info("Board List Page Redirect");
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage() {
		log.info("Board List Page");
		return "customer/board/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage() {
		log.info("Board Create Page");
		return "customer/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo() {
		log.info("Board Create Do");
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/modify")
	public String ModifyPage() {
		log.info("Board Modify Page");
		return "customer/board/update";
	}
	
	@PostMapping(value="/modify")
	public String ModifyDo() {
		log.info("Board Modify Do");
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage() {
		log.info("Board View Page");
		return "customer/board/view";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo() {
		log.info("Board Delete Do");
		return "redirect:/boards/list";
	}
}
