package com.com.com.erp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.KakaoApiUtil;
import com.com.com.erp.dao.MemberDao;
import com.com.com.erp.dao.ProxyDao;
import com.com.com.erp.dto.ApiUserDto;
import com.com.com.erp.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	@Autowired
	private ProxyService pServ;
	@Autowired
	private MemberDao mDao;
	@Autowired
	private ProxyDao pDao;
	@Autowired
	private KakaoApiUtil util;

	public List<Map<String, Object>> getCartList() {
		List<Map<String, Object>> ctList = mDao.getCartList();
		log.info("11"+ctList);
		return ctList;
	}

	public Map<String, String> insertCart(Map<String, Object> cartItem) {
		Map<String, String> response = new HashMap<String, String>();

		// �α��ε� ����� ID
		String memberId = (String) cartItem.get("MEMBER_ID");
		String pizzaName = (String) cartItem.get("CART_TITLE");
		String doughType = (String) cartItem.get("CART_DOW");
		String size = (String) cartItem.get("CART_SIZE");
		
		// ���� ��ٱ��� ����Ʈ ��������
		List<Map<String, Object>> ctList = mDao.getCartList();
		
		// ���� ���� ���� �÷���
		boolean isUpdated = false;

		// ���� ��ٱ��� �׸�� ��
		for (Map<String, Object> ct : ctList) {
			boolean sameId = memberId.equals(ct.get("ID"));
			boolean samePizza = pizzaName.equals(ct.get("CART_TITLE"));
			boolean sameDough = doughType.equals(ct.get("CART_DOW"));
			boolean sameSize = size.equals(ct.get("CART_SIZE"));

			if (sameId && samePizza && sameDough && sameSize) {
				
				// ���� 1 ���� ����
				BigDecimal currentCountBigDecimal = (BigDecimal) ct.get("CART_COUNT");
				int currentCount = currentCountBigDecimal != null ? currentCountBigDecimal.intValue() : 0;
				BigDecimal currentPriceBigDecimal = (BigDecimal) ct.get("CART_PRICE");
				int currentPrice = currentPriceBigDecimal != null ? currentPriceBigDecimal.intValue() : 0;

				// CART_PRICE ó��
				Object cartPriceObj = cartItem.get("CART_PRICE");
				int cartPriceInt;

				if (cartPriceObj instanceof Integer) {
					cartPriceInt = (Integer) cartPriceObj;
				} else if (cartPriceObj instanceof String) {
					try {
						cartPriceInt = Integer.parseInt((String) cartPriceObj);
					} catch (NumberFormatException e) {
						response.put("status", "fail");
						response.put("message", "CART_PRICE ���� ����.");
						return response;
					}
				} else {
					response.put("status", "fail");
					response.put("message", "CART_PRICE Ÿ�� ����.");
					return response;
				}

				// ���� ����
				ct.put("CART_COUNT", BigDecimal.valueOf(currentCount + 1));
				ct.put("CART_PRICE", BigDecimal.valueOf(currentPrice + cartPriceInt));
				int updateRe = mDao.updateCart(ct);
				if (updateRe > 0) {
					response.put("status", "ok");
					response.put("message", "��ǰ�� ��ٱ��Ͽ� �����ϴ�.");
				} else {
					response.put("status", "fail");
					response.put("message", "��ǰ �߰� �� ������ �߻��߽��ϴ�.");
				}
				isUpdated = true;
				break;
			}
		}

		if (!isUpdated) {
			
			int insertRe = mDao.insertCart(cartItem);
			if (insertRe > 0) {
				response.put("status", "ok");
				response.put("message", "��ǰ�� ��ٱ��Ͽ� �����ϴ�.");
			} else {
				response.put("status", "fail");
				response.put("message", "��ǰ �߰� �� ������ �߻��߽��ϴ�.");
			}
		}

		return response;
	}

	public Map<String, String> updateCart(Map<String, Object> cartList) {
		Map<String, String> response = new HashMap<String, String>();
		int updateRe;
			if(cartList.size() == 1) {
				updateRe = mDao.deleteCart(cartList);
			} else {
				Object ccc = cartList.get("CART_COUNT");
				int aaa = (Integer) ccc;
				if (aaa == 0) {
					updateRe = mDao.deleteCart(cartList);
					
				} else {
					updateRe = mDao.updateCart(cartList);
					
				}
			}
		
		if (updateRe != 0) {
			response.put("status", "done");
		} else {
			response.put("status", "fail");
		}
		return response;
	}

	public List<Map<String, Object>> getPizzaList() {
		List<Map<String, Object>> pzList = mDao.getPizzaList();
		
		return pzList;
	}

	public Map<String, Object> reactLoginProc(Map<String, Object> loginUser) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> dbUser = mDao.reactLoginUser(loginUser);
		
		log.info("���� �α���proc" + loginUser);
		if (dbUser != null) {
			if (!dbUser.get("MEMBER_PASSWORD").equals(loginUser.get("password"))) {
				response.put("user",dbUser);
				response.put("message", "��й�ȣ�� �ٸ��ϴ�.");
			} else {
				response.put("user",dbUser);
				response.put("message", "ȯ���մϴ� "+dbUser.get("MEMBER_NAME")+"��!");
			}
		} else {
			response.put("user",dbUser);
			response.put("message", "���Ե� ���̵� �����ϴ�.");
		}
		return response;
	}
	
	public String apiJoinProc(ApiUserDto apiUser,RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		String email1 = apiUser.getApiEmail1();
		String email2 = apiUser.getApiEmail2();
		apiUser.setApiEmail1(email1 + "@" + email2);
		
		int apiRe = mDao.apiJoinProc(apiUser);
		
		if(apiRe != 0) {
			msg = "���� �Ϸ�";
			view = "/login";
		} else {
			msg = "���� ����";
			view = "/apiJoin";
		}
		rttr.addFlashAttribute("msg",msg);
		return view;
	}

	public Map<String, String> joinProc(Map<String, Object> user) {
		Map<String, String> response = new HashMap<String, String>();
		int nextId = mDao.nextId();
		
		user.put("id", nextId);
		
		int result = mDao.joinProc(user);

		if (result != 0) {
			response.put("status", "ok");
		} else {
			response.put("status", "error");
		}

		return response;
	}

	public String loginProc(MemberDto member, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;
//		LocalDate today = LocalDate.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formattedDate = today.format(formatter);
//		log.info("����= " + formattedDate);

		// �ٸ���¥ �׽�Ʈ��
//	        LocalDate today1 = LocalDate.now();
//	        LocalDate tomorrow = today1.plusDays(1);
//	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        String formattedDate1 = tomorrow.format(formatter1);
//	        log.info("����= " + formattedDate1);
		int idRe = mDao.idCheck(member.getMemberId());
		if (idRe == 0) {
			log.info("�α���proc�� null");
			msg = "�ش��ϴ� ���̵� �����ϴ�.";
			view = "redirect:/login";
		} else {
			member = mDao.login(member);
			if (member != null) {
				msg = "�ȳ��ϼ��� " + member.getMemberName() + "��";
//				//�ּ� ��ǥ�� ��ȯ
//				String[] coordinates = util.getAddressPoint(member.getAddress2());
//				log.info("����"+coordinates);
//				String lat = coordinates[0];
//				String lng = coordinates[1];

				// ������ ��ǥ�� ���
//				log.info("Latitude: " + lat + ", Longitude: " + lng);
				session.setAttribute("member", member);
				view = "redirect:/apiMain";
			} else {
				msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
				view = "redirect:/login";
			}
		}
		rttr.addFlashAttribute("msg", msg);
		return view;
	}

//	public int updateRank(MemberDto proxyMember) {
//		log.info("mServ.updateRank()");
//		if(proxyMember != null) {
//			int updateResult = mDao.updateRank(proxyMember);
//			return updateResult;
//		} else {
//			return 0;
//		}
//	}	

}
