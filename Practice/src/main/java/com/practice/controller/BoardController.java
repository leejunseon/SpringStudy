package com.practice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.domain.BoardVO;
import com.practice.domain.Criteria;
import com.practice.domain.DataTableDto;
/*import com.practice.domain.PageDTO;*/
import com.practice.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@PostMapping(value="/example",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) 
	@ResponseBody 
	public ResponseEntity<DataTableDto> example(DataTableDto dto, @RequestBody MultiValueMap<String, String> formData){ 
	    int draw = Integer.parseInt(formData.get("draw").get(0)); 
	    int start = Integer.parseInt(formData.get("start").get(0)); 
	    int length = Integer.parseInt(formData.get("length").get(0)); 

	    int total = service.getTotal(); 
	    List<BoardVO> data = service.findData(start, length); 

	    dto.setDraw(draw); 
	    dto.setRecordsFiltered(total); 
	    dto.setRecordsTotal(total); 
	    dto.setData(data); 

	    return new ResponseEntity<>(dto,HttpStatus.OK); 
	}

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
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
	public void get(@RequestParam("bno")Long bno,Model model) {//@RequestParam �����ص� ����. �Ķ���� �̸��� ���� �̸��� �������� �����ϱ� ����
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
