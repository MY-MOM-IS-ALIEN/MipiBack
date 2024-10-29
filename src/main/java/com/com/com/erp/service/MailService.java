package com.com.com.erp.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Component
@Slf4j
public class MailService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
	// 난수 발생(여러분들 맘대러)
	
		public void makeRandomNumber() {
			// 난수의 범위 111111 ~ 999999 (6자리 난수)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			log.info("인증번호 : " + checkNum);
			authNumber = checkNum;
		}
		
		
				//이메일 보낼 양식! 
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = ".com"; 
			String toMail = email;
			String title = "회원 가입 인증 이메일 입니다."; 
			String content = 
					"홈페이지를 방문해주셔서 감사합니다." + 	
	                "<br><br>" + 
				    "인증 번호는 " + authNumber + "입니다." + 
				    "<br>" + 
				    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; 
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//이메일 전송 메소드
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSVKAF2CH1C4MJX", "OULEZUS8VWRRBMZ8LFYUOVNEHP7AL139", "https://api.coolsms.co.kr");
		
		public void certifiedPhoneNumber(String phoneNumber, String numStr) {
	        // 메시지 객체 생성
	        Message message = new Message();
	        message.setFrom("01071526196");
	        message.setTo(phoneNumber);
	        message.setText("인증번호는 [" + numStr + "] 입니다");

	        try {
	            // 메시지 보내기
	            SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
	            System.out.println(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
