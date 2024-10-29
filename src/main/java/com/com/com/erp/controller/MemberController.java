package com.com.com.erp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.KakaoApiUtil;
import com.com.com.erp.dto.ApiUserDto;
import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@RestController
public class MemberController {
	@Autowired 
	private MemberService mServ;
	
	@PostMapping("/getCartList")
	@ResponseBody
	public List<Map<String, Object>> getCartList() {

		return mServ.getCartList();
	}
	
	@PostMapping("/insertCart")
	@ResponseBody
	public Map<String, String> insertCart(@RequestBody Map<String, Object> cartList){
		log.info("컨트롤러 도착한"+cartList);
		log.info("zz"+cartList.size());
		if(cartList.size() > 5) {
			log.info("1");
			return mServ.insertCart(cartList);
		} else {
			log.info("2");
			return mServ.updateCart(cartList);
		}
		
	}
	
	@GetMapping("/getPizzaList")
	@ResponseBody
	
	public List<Map<String, Object>> getPizzaList() {
		return mServ.getPizzaList();
	}
	
	@PostMapping("/joinProc")
	@ResponseBody
	public Map<String, String> joinProc(@RequestBody Map<String, Object> user) {
	    log.info("컨트롤러"+user);

	    return mServ.joinProc(user);
	}
	
	@PostMapping("/reactLoginProc")
	@ResponseBody
	public Map<String, Object> reactLoginProc(@RequestBody Map<String,Object> loginUser){
		log.info("컨트롤러"+loginUser);
		
		return mServ.reactLoginProc(loginUser);
	}
	
	@GetMapping("/apiJoin")
	public String apiJoin() {
		
		return "apiJoin";
	}
	
	@PostMapping("/apiJoinProc")
	public String apiJoinProc(ApiUserDto apiUser,RedirectAttributes rttr) {
		log.info("넘어온 것"+apiUser);
		
		String view = mServ.apiJoinProc(apiUser,rttr);
		return view;
	}
	
	@GetMapping("/apiMain")
	public String apiMain(MemberDto member,
			RedirectAttributes rttr,
			HttpSession session) {
		
		return "apiMain";
	}

	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@PostMapping("/loginProc")
	public String loginProc(MemberDto member,
							RedirectAttributes rttr,
							HttpSession session) {
		
		String view = mServ.loginProc(member,rttr,session);
		return view;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,RedirectAttributes rttr) {
		String msg = null;
		
		session.invalidate();
		msg = "로그아웃 되었습니다.";
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/login";
	}
	
	@GetMapping("/loginChange")
	public String loginChange(@RequestParam("selectNo") int selectNo,
								RedirectAttributes rttr,
								HttpSession session) {
		MemberDto member = new MemberDto();
		
		switch (selectNo) {
	    case 1:
	       	member.setMemberId("user001");
	       	member.setPassword("password1");
	        break;
	    case 2:
	    	member.setMemberId("user005");
	    	member.setPassword("password5"); 
	        break;
	    case 3:
	    	member.setMemberId("user007");
	    	member.setPassword("password7");
	        break;
	    case 4:
	    	member.setMemberId("user010");
	    	member.setPassword("password10");
	        break;
		}
		String view = mServ.loginProc(member,rttr,session);
		return view;
	}
}
