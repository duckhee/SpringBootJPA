package com.example.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Boards;
import com.example.persistence.BoardCustomCrudRepository;
import com.example.util.PageMaker;
import com.example.util.PageVo;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/boards")
public class BoardController {
	
	@Autowired
	private BoardCustomCrudRepository repo;

	@GetMapping(value= {"", "/"})
	public String MainPage() {
		log.info("Board List Page Redirect");
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage(@ModelAttribute("pageVo")PageVo vo, Model model) {
		log.info("Board List Page");
		Pageable page = vo.makePageable(0, "idx");
		Page<Object[]> getList = repo.getPaging(vo.getType(), vo.getKeyword(), page);
		log.info("" + page);
		log.info("" + getList);
		model.addAttribute("list", new PageMaker<>(getList));
		return "customer/board/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage(@ModelAttribute("board")Boards board) {
		log.info("Board Create Page");
		return "customer/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo(@ModelAttribute("board")Boards board, RedirectAttributes flash) {
		log.info("Board Create Do");
		log.info("get Board : " + board);
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/modify")
	public String ModifyPage(@ModelAttribute("board")Boards baord) {
		log.info("Board Modify Page");
		return "customer/board/update";
	}
	
	@PostMapping(value="/modify")
	public String ModifyDo(@ModelAttribute("board")Boards board, RedirectAttributes flash) {
		log.info("Board Modify Do");
		return "redirect:/boards/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage(HttpServletRequest request) {
		log.info("Board View Page");
		log.info("get Parameter :" + request.getParameter("bno"));
		return "customer/board/view";
	}
	
	@PostMapping(value="/uploadImg")
	public String UploadImg() {
		log.info("Upload Image");
		return "";
	}
	
	@PostMapping(value="/uploadFile")
	public String UploadFile(MultipartFile[] uploadFile, Model model) {
		log.info("upload File");
		return "";
	}
	
	@PostMapping(value="/download")
	@ResponseBody
	public ResponseEntity<Resource> download(String FileName){
		return null;
	}
	
	
	@PostMapping(value="/delete")
	public String DeleteDo() {
		log.info("Board Delete Do");
		return "redirect:/boards/list";
	}
}
