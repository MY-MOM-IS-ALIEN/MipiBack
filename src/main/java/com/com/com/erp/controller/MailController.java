package com.com.com.erp.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.com.com.erp.VerifyRecaptcha;
import com.com.com.erp.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MailController {
	@Autowired
	private MailService mailService;
	
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		log.info("�̸��� ���� ��û�� ����!");
		log.info("�̸��� ���� �̸��� : " + email);
		
		return mailService.joinEmail(email);
	}
	
	@PostMapping("/phoneCheck") //jsp ������ �ѱ� mapping ��
	@ResponseBody    
	    public String sendSMS(String phoneNumber) {
	 
	        Random rand  = new Random(); //�������� �����ϱ� !!
	        String numStr = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }
	        log.info(phoneNumber);
	        log.info(numStr);
	        
	        mailService.certifiedPhoneNumber(phoneNumber, numStr); 
	
	         
	          return numStr;
	    }
	
	@ResponseBody
	@RequestMapping(value = "VerifyRecaptcha", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
	    VerifyRecaptcha.setSecretKey("6LdxUlcqAAAAAD_1e452VLZ7VQgIiitHMlve5ff2");
	    String gRecaptchaResponse = request.getParameter("recaptcha");
	    try {
	       if(VerifyRecaptcha.verify(gRecaptchaResponse))
	          return 0; // ����
	       else return 1; // ����
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1; //����
	    }
	}
	
}
