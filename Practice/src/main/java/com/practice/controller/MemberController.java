package com.practice.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
	private BCryptPasswordEncoder pwencoder;

	@GetMapping("/memberRegister")
	public void memberRegister() {
		log.info("MemberController : <Get> memberRegister");
	}
	
	@Transactional
	@PostMapping("/memberRegister")
	public String memberRegister(MemberVO member,RedirectAttributes rttr) {
		log.info("MemberController : <Post> memberRegister");
		String resultPage="";
		if(service.checkReduplication(member.getUserid())==0) {
			AuthVO auth=new AuthVO();
			auth.setUserid(member.getUserid());
			auth.setAuth("user");
			member.setUserpw(pwencoder.encode(member.getUserpw()));
			if(service.insertMember(member)!=0&&service.insertAuth(auth)!=0) {
				rttr.addFlashAttribute("result",member.getUserid());
				resultPage="redirect:/customLogin";
			}
		}else {
			rttr.addFlashAttribute("result",member.getUserid()+"�� �ߺ��Ǵ� ���̵��Դϴ�. �ٸ� ���̵� ������ּ���.");
			resultPage="redirect:/member/memberRegister";
		}
		return resultPage;
	}
	
	@GetMapping("/findPassword")
	public void findPassword() {
		log.info("MemberController : <Get> findPassword");
	}
	
	@GetMapping("/resetPassword")
	public void resetPassword(String id,Model model) {
		log.info("MemberController : <Get> resetPassword: "+id);
		model.addAttribute("userid",id);
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(MemberVO member) {
		String resultPage="";
		log.info("MemberController : <Post> resetPassword: "+member);
		member.setUserpw(pwencoder.encode(member.getUserpw()));
		if(service.updateMember(member)==1)
			resultPage="redirect:/customLogin";
		
		return resultPage;
	}
}
