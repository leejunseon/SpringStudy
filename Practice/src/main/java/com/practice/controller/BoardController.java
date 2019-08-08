package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.domain.BoardVO;
import com.practice.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list",service.getList());
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		log.info("register: "+board);
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")Long bno,Model model) {//@RequestParam 생략해도 무방. 파라미터 이름과 변수 이름을 기준으로 동작하기 때문
		log.info("get or modify: "+bno);
		model.addAttribute("board",service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,RedirectAttributes rttr) {
		log.info("modify: "+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno,RedirectAttributes rttr) {
		log.info("remove: "+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
}
