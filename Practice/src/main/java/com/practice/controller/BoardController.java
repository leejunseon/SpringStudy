package com.practice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.domain.BoardVO;
import com.practice.domain.DataTableDto;
import com.practice.domain.BoardPagingDto;
import com.practice.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@PostMapping(value="/tableSetting",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) 
	@ResponseBody 
	public DataTableDto tableSetting(DataTableDto dto, @RequestBody MultiValueMap<String, String> formData){ 
		log.info("BoardController : <Post> tableSetting");
	    int draw = Integer.parseInt(formData.get("draw").get(0)); 
	    String start = formData.get("start").get(0); 
	    String length = formData.get("length").get(0); 
	    String orderNum=null;
	    String orderDir=null;
	    if(formData.containsKey("order[0][column]")) {
	    	orderNum=formData.get("order[0][column]").get(0);
	    	orderDir=formData.get("order[0][dir]").get(0);
	    }
	    String search=formData.get("search[value]").get(0);
	    
	    BoardPagingDto paging=new BoardPagingDto(start,length,orderNum,orderDir,search);
	    //orderNum
	    //0 : bno
	    //1 : title
	    //2 : writer
	    //3 : regdate
	    //4 : updatedate
	    
	    int total = service.getTotal(); 
	    List<BoardVO> data = service.getList(paging); 

	    dto.setDraw(draw); 
	    dto.setRecordsFiltered(total); 
	    dto.setRecordsTotal(total); 
	    dto.setData(data); 

	    return dto;
	}

	@GetMapping("/list")
	public void list() {
		log.info("BoardController : <Get> list");
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("BoardController : <Get> register");
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board,RedirectAttributes rttr) {
		log.info("BoardController : <Post> register");
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")Long bno,Model model) {
		log.info("BoardController : <Get> get or modify "+bno);
		model.addAttribute("board",service.get(bno));
	}
	
	@PreAuthorize("principal.username==#board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board,RedirectAttributes rttr) {
		log.info("BoardController : <Post> modify");
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	@PreAuthorize("principal.username==#writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno,String writer,RedirectAttributes rttr) {
		log.info("BoardController : <Post> remove "+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
}
