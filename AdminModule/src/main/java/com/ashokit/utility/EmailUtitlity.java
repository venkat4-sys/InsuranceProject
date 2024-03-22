package com.ashokit.utility;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtitlity {
	
	@Autowired
	private JavaMailSender mailsender;
	
	private Logger loger=LoggerFactory.getLogger(EmailUtitlity.class);
	
	
   public boolean sendEmail(String subject,String body,String to) {
		
		try {
			MimeMessage mimsg = mailsender.createMimeMessage();
			
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimsg,true);
			
			msgHelper.setSubject(subject);
			msgHelper.setText(body,true);
			msgHelper.setTo(to);
			
			

			mailsender.send(mimsg);
			
		}catch(Exception e) {
			loger.error(e.getMessage());
		}
		
		return true;
		
	}

}
