package com.com.com.erp.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.dao.ProxyDao;
import com.com.com.erp.dto.ProxyDto;

@Service
public class ProxyService {
	@Autowired
	private ProxyDao pDao;

	public String updateProxy(ProxyDto proxy, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;
		
		if(proxy != null) {
			int updateResult = pDao.updateProxy(proxy);
			if(updateResult > 0) {
				msg = "대리결재자 선임 완료";
				view = "redirect:/main";
			} else {
				msg = "대리결재자 선임 실패";
				view = "redirect:/proxy";
			}
		} else {
			msg = "대리결재 업데이트 중 오류";
			view = "redirect:/proxy";
		}
		rttr.addFlashAttribute("msg",msg);
		return view;
	}
	
}
