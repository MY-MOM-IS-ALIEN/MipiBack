package com.com.com.erp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.dto.ProxyDto;
import com.com.com.erp.service.BoardService;
import com.com.com.erp.service.MemberService;
import com.com.com.erp.service.ProxyService;

@Controller
public class ProxyController {
	@Autowired private BoardService bServ;
	@Autowired private ProxyService pServ;
	@Autowired private MemberService mServ;

	@GetMapping("/proxy")
	public String proxy(HttpSession session,Model model) {
		MemberDto member = bServ.getSession(session);
		model.addAttribute("member",member);
		return "proxy";
	}
	
	@PostMapping("/proxyProc")
	public String proxyProc(ProxyDto proxy, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;
		MemberDto member = bServ.getSession(session);

		// null이 아니면 처리
		if (proxy != null) {
			proxy.setProxyRank(member.getMemberRank());
			proxy.setApperId(member.getMemberId());
			view = pServ.updateProxy(proxy, rttr, session);
		} else {
			msg = "선택한 사원을 찾을 수 없습니다.";
			view = "redirect:/proxy";
		}
		rttr.addFlashAttribute("msg",msg);
		return view;
	}
}
