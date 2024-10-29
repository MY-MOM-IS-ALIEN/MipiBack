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
	// ���� �߻�(�����е� ���뷯)
	
		public void makeRandomNumber() {
			// ������ ���� 111111 ~ 999999 (6�ڸ� ����)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			log.info("������ȣ : " + checkNum);
			authNumber = checkNum;
		}
		
		
				//�̸��� ���� ���! 
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = ".com"; 
			String toMail = email;
			String title = "ȸ�� ���� ���� �̸��� �Դϴ�."; 
			String content = 
					"Ȩ�������� �湮���ּż� �����մϴ�." + 	
	                "<br><br>" + 
				    "���� ��ȣ�� " + authNumber + "�Դϴ�." + 
				    "<br>" + 
				    "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���."; 
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//�̸��� ���� �޼ҵ�
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true �Ű����� �����ϸ� multipart ������ �޼��� ������ ����.���� ���ڵ� ������ �����ϴ�.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ���� > html �������� ���� , �ۼ����� ������ �ܼ� �ؽ�Ʈ�� ����.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSVKAF2CH1C4MJX", "OULEZUS8VWRRBMZ8LFYUOVNEHP7AL139", "https://api.coolsms.co.kr");
		
		public void certifiedPhoneNumber(String phoneNumber, String numStr) {
	        // �޽��� ��ü ����
	        Message message = new Message();
	        message.setFrom("01071526196");
	        message.setTo(phoneNumber);
	        message.setText("������ȣ�� [" + numStr + "] �Դϴ�");

	        try {
	            // �޽��� ������
	            SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
	            System.out.println(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
