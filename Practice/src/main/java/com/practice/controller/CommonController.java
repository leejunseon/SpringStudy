package com.practice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth,Model model) {
		log.info("CommonController : <Get> accessDenied");
		model.addAttribute("msg","AccessDenied");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error,String logout,Model model) {
		log.info("CommonController : <Get> customLogin error:"+error+" logout:"+logout);
		
		if(error!=null)
			model.addAttribute("error","Login Error Check Your Account");
		
		if(logout!=null)
			model.addAttribute("logout","Logout!!");
	}
	
	@GetMapping("/memberRegister")
	public void memberRegister() {
		log.info("CommonController : <Get> memberRegister");
	}
	
	@PostMapping("/memberRegister")
	public String memberRegister(MemberVO member) {
		
		return "redirect:/customLogin";
	}
	
	@GetMapping("/findPassword")
	public void findPassword() {
		log.info("CommonController : <Get> findPassword");
	}
	
	@PostMapping("/customLogout")
	public void logoutPost() {
		log.info("CommonController : <Post> customLogout");
	}
}
