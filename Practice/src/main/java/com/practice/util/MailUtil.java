package com.practice.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.practice.domain.MailVO;

import lombok.Setter;

@Component
public class MailUtil {
	
	@Setter(onMethod_= {@Autowired})
	private JavaMailSender mailSender;
	
	public boolean send(MailVO mail) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
	    	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	 
	    	messageHelper.setFrom(mail.getFrom());  // 보내는사람 생략하거나 하면 정상작동을 안함
	    	messageHelper.setTo(mail.getTo());     // 받는사람 이메일
	    	messageHelper.setSubject(mail.getTitle()); // 메일제목은 생략이 가능하다
	    	messageHelper.setText(mail.getContent(),true);  // 메일 내용
	     
	    	mailSender.send(message);
	    } catch(Exception e){
	    	return false;
	    }
		return true;
	}
}
