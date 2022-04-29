package com.example.M3.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail( String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom("vasadioffical@gmail.com");//Set Mail id in application.propertied
			message.setTo("niranjan.vasadi@gmail.com"); //Mention User mail id to sent mail
			message.setText(body);
			message.setSubject("Warning For OverSpeed");
			mailSender.send(message);
			System.out.println("Mail Sent");
			
		} catch (Exception e) {
			System.out.println("Something Wrong with Mail Part");
		}
		
	}

}
