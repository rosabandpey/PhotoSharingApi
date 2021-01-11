package com.Rosa.PhotoSharingApi.utility;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.Rosa.PhotoSharingApi.model.User;

@Component
public class EmailConstructor {

	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine tempEngine;
	
	
	public MimeMessagePreparator contructNewUserEmail(User user,String password) {
		
		Context context=new Context();
		context.setVariable("user", user);
		context.setVariable("password", password);
		String text=tempEngine.process("newUserEmailTemplate", context);
		
		
		MimeMessagePreparator messagePreparator=new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage)  throws Exception{
				
				MimeMessageHelper  email=new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Welcome to Rosa Commiunity");
				email.setText(text,true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
								
			}
			
		};
		
		return messagePreparator;
	}
	
	
	public MimeMessagePreparator constructUpdateUserProfileEmail(User user) {
		Context context = new Context();
		context.setVariable("user", user);
		String text = tempEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("Profile Update - Rosa");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
	
	
	public MimeMessagePreparator constructResetPasswordEmail(User user, String password) {
		Context context = new Context();
		context.setVariable("user", user);
		context.setVariable("password", password);
		String text = tempEngine.process("resetPasswordEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(user.getEmail());
				email.setSubject("New Password - Rosa");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
	
}
