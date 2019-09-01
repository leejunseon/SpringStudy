package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.domain.MailVO;
import com.practice.mapper.MemberMapper;
import com.practice.util.MailUtil;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mail/*")
public class MailController {
	
	@Setter(onMethod_= {@Autowired})
	MailUtil mailUtil;
	
	@Setter(onMethod_= {@Autowired})
	MemberMapper mapper;
	
	@PostMapping(value = "/send")
	public String mailSending(@RequestParam String id,MailVO mail,RedirectAttributes rttr) {
		log.info("MailController : <Post> mailSending");
		
		int emailNum=mapper.getEmail(id,mail.getTo());
		if(emailNum==0) {
			rttr.addFlashAttribute("message","��ȿ�� ������ �ƴմϴ�. �ٽ� Ȯ�����ּ���");
			return "redirect:/member/findPassword";
		}
			
		mail.setFrom("ljs921026@gmail.com");
		mail.setTitle("[Password Reset]");
		mail.setContent(new StringBuffer()
				.append("<h1>[Password Reset]</h1>")
				.append("<p>Hi, "+id+".</p>")
				.append("<p>Click below to reset your password</p>")
				.append("<a href='http://localhost:8080/member/resetPassword?id=")
				.append(id)
				.append("' target='_blenk'>"+id+"�̸��� ���� Ȯ��</a>")
				.toString());
		
		boolean result=mailUtil.send(mail);
		
		if(result) {
			rttr.addFlashAttribute("message","Email�� ���۵Ǿ����ϴ�. Ȯ�����ֽñ� �ٶ��ϴ�.");
			return "redirect:/customLogin";
		}else {
			rttr.addFlashAttribute("message","Error");
			return "redirect:/member/findPassword";
		}
	}
	
}
