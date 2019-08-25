package com.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.domain.Replies;
import com.practice.domain.ReplyPagingDto;
import com.practice.domain.ReplyVO;
import com.practice.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//ResponseEntity객체 사용 안하고 데이터만 보내도됨. consumes를 지정하면 지정됨 타입이외의 데이터는 받지 않음. produces는 지정해줘야함.
	@PostMapping(value="/new",consumes="application/json",produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		int insertCount=service.register(vo);
		log.info("Reply insert count:"+insertCount);
		return insertCount==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Replies>getList(@PathVariable("bno")Long bno,@PathVariable("page")int page){
		log.info("getList:"+bno);
		ReplyPagingDto paging = new ReplyPagingDto(page, 10);
		return new ResponseEntity<>(service.getReplies(bno,paging),HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO>get(@PathVariable("rno")Long rno){
		log.info("get"+rno);
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}",produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String>remove(@PathVariable("rno")Long rno){
		log.info("remove:"+rno);
		return service.remove(rno)==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH},value="/{rno}",consumes="application/json",produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String>modify(@RequestBody ReplyVO vo,@PathVariable("rno")Long rno){
		vo.setRno(rno);
		log.info("modify:"+vo);
		return service.modify(vo)==1?new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
