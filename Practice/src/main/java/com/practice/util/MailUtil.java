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
	 
	    	messageHelper.setFrom(mail.getFrom());  // �����»�� �����ϰų� �ϸ� �����۵��� ����
	    	messageHelper.setTo(mail.getTo());     // �޴»�� �̸���
	    	messageHelper.setSubject(mail.getTitle()); // ���������� ������ �����ϴ�
	    	messageHelper.setText(mail.getContent(),true);  // ���� ����
	     
	    	mailSender.send(message);
	    } catch(Exception e){
	    	return false;
	    }
		return true;
	}
}
