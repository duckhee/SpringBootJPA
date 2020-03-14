package com.example.admin.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.example.admin.persistence.AdminBoardCustomCrudRepository;
import com.example.admin.persistence.AdminBoardRepository;
import com.example.domain.Boards;
import com.example.util.PageMaker;
import com.example.util.PageVo;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/admin/boards")
public class AdminBoardController {
	
	@Autowired
	private AdminBoardCustomCrudRepository repo;
	
	@GetMapping(value= {"","/"})
	public String MainPage() {
		log.info("Admin Board Main Redirect");
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/create")
	public String CreatePage(@ModelAttribute("Board") Boards board, Model models, HttpServletRequest req) {
		log.info("Admin Board Create Page");
		
		return "admin/board/create";
	}
	
	@PostMapping(value="/create")
	public String CreateDo(@ModelAttribute("Board") Boards board, PageVo page, HttpServletRequest request, RedirectAttributes redirectFlash) {
		log.info("Admin Board Create Do");
		log.info("board Value : " + board);
		if(board.getTitle() == null) {
			log.info("title is not null");
			redirectFlash.addFlashAttribute("msg", "Title Need");
			return "redirect:/admin/boards/create";
		}
		if(board.getWriter() == null || board.getWriter() == "") {
			log.info("not login User");
			redirectFlash.addFlashAttribute("msg", "Login First");
			return "redirect:/admin/users/login";
		}
		/** Board Save */
		repo.save(board);
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/list")
	public String ListPage(@ModelAttribute("pageVo")PageVo vo,Model model) {
		log.info("Admin Board List Page");
		Pageable page = vo.makePageable(0, "idx");
		Page<Object[]> getList = repo.getPaging(vo.getType(), vo.getKeyword(), page);
		log.info("" + page);
		log.info("" + getList);
		model.addAttribute("list", new PageMaker<>(getList));
		return "admin/board/list";
	}
	
	@GetMapping(value="/update")
	public String ModifyPage(HttpServletRequest request, Model model) {
		log.info("Admin Board Modify Page");
		try {
			Long bno = (long)Integer.parseInt(request.getParameter("bno"));
			log.info("get Bno :" + bno);
			repo.findById(bno).ifPresent(boards->{
				log.info("get Board :" + boards);
				model.addAttribute("board", boards);
			});
			return "admin/board/update";
		}catch (NumberFormatException e) {
			// TODO: handle exception
			log.info("not nubmer format");
			return "redirect:/admin/boards/list";
		}
	}
	
	@PostMapping(value="/update")
	public String ModifyDo(@ModelAttribute("Board") Boards board, HttpServletRequest request, RedirectAttributes flash) {
		log.info("Admin Board Modify Do");
		log.info("board " + board);
		return "redirect:/admin/boards/list";
	}
	
	@GetMapping(value="/view")
	public String ViewPage(HttpServletRequest request, Model model) {
		log.info("Admin Board View Page");
		/** get board Detail View use board.idx parameter Type is bno */
		try {
			Long bno = (long) Integer.parseInt(request.getParameter("bno"));
			log.info("get BNO :" + bno);
			repo.findById(bno).ifPresent(board->{
				log.info("get Board :" + board);
				model.addAttribute("Board", board);
			});
			return "admin/board/view";
		}catch (NumberFormatException e) {
			// TODO: handle exception
			log.info("not number format");
			return "redirect:/admin/boards/list";
		}
	}
	
	@PostMapping(value="/uploadImg")
	public String UploadImg() {
		log.info("Upload Image");
		return "";
	}
	
	@PostMapping(value="/uploadFile")
	public String UploadFile() {
		log.info("upload File");
		return "";
	}
	
	@PostMapping(value="/delete")
	public String DeleteDo(HttpServletRequest request, PageVo page, RedirectAttributes flash) {
		log.info("Admin Board Delete Do");
		return "redirect:/admin/boards/list";
	}
}
