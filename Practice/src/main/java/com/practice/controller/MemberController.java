package com.practice.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;
import com.practice.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {
	
	private MemberService service;

	@GetMapping("/memberRegister")
	public void memberRegister() {
		log.info("CommonController : <Get> memberRegister");
	}
	
	@Transactional
	@PostMapping("/memberRegister")
	public String memberRegister(MemberVO member,RedirectAttributes rttr) {
		log.info("CommonController : <Post> memberRegister");
		String result="";
		BCryptPasswordEncoder pwencoder=new BCryptPasswordEncoder();
		if(service.checkReduplication(member.getUserid())==0) {
			AuthVO auth=new AuthVO();
			auth.setUserid(member.getUserid());
			auth.setAuth("user");
			member.setUserpw(pwencoder.encode(member.getUserpw()));
			if(service.insertMember(member)!=0&&service.insertAuth(auth)!=0) {
				rttr.addFlashAttribute("result",member.getUserid());
				result="redirect:/customLogin";
			}
		}else {
			rttr.addFlashAttribute("result",member.getUserid()+"는 중복되는 아이디입니다. 다른 아이디를 사용해주세요.");
			result="redirect:/member/memberRegister";
		}
		return result;
	}
	
	@GetMapping("/findPassword")
	public void findPassword() {
		log.info("CommonController : <Get> findPassword");
	}
}
