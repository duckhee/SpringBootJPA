package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class MainController {

	@GetMapping(value="")
	public String MainPage() {
		log.info("Main Page");
		return "/customer/index";
	}
}
