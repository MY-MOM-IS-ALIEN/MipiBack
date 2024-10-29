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

		// 로그인된 사용자 ID
		String memberId = (String) cartItem.get("MEMBER_ID");
		String pizzaName = (String) cartItem.get("CART_TITLE");
		String doughType = (String) cartItem.get("CART_DOW");
		String size = (String) cartItem.get("CART_SIZE");
		
		// 현재 장바구니 리스트 가져오기
		List<Map<String, Object>> ctList = mDao.getCartList();
		
		// 수량 증가 여부 플래그
		boolean isUpdated = false;

		// 기존 장바구니 항목과 비교
		for (Map<String, Object> ct : ctList) {
			boolean sameId = memberId.equals(ct.get("ID"));
			boolean samePizza = pizzaName.equals(ct.get("CART_TITLE"));
			boolean sameDough = doughType.equals(ct.get("CART_DOW"));
			boolean sameSize = size.equals(ct.get("CART_SIZE"));

			if (sameId && samePizza && sameDough && sameSize) {
				
				// 수량 1 증가 로직
				BigDecimal currentCountBigDecimal = (BigDecimal) ct.get("CART_COUNT");
				int currentCount = currentCountBigDecimal != null ? currentCountBigDecimal.intValue() : 0;
				BigDecimal currentPriceBigDecimal = (BigDecimal) ct.get("CART_PRICE");
				int currentPrice = currentPriceBigDecimal != null ? currentPriceBigDecimal.intValue() : 0;

				// CART_PRICE 처리
				Object cartPriceObj = cartItem.get("CART_PRICE");
				int cartPriceInt;

				if (cartPriceObj instanceof Integer) {
					cartPriceInt = (Integer) cartPriceObj;
				} else if (cartPriceObj instanceof String) {
					try {
						cartPriceInt = Integer.parseInt((String) cartPriceObj);
					} catch (NumberFormatException e) {
						response.put("status", "fail");
						response.put("message", "CART_PRICE 형식 오류.");
						return response;
					}
				} else {
					response.put("status", "fail");
					response.put("message", "CART_PRICE 타입 오류.");
					return response;
				}

				// 수량 증가
				ct.put("CART_COUNT", BigDecimal.valueOf(currentCount + 1));
				ct.put("CART_PRICE", BigDecimal.valueOf(currentPrice + cartPriceInt));
				int updateRe = mDao.updateCart(ct);
				if (updateRe > 0) {
					response.put("status", "ok");
					response.put("message", "상품이 장바구니에 담겼습니다.");
				} else {
					response.put("status", "fail");
					response.put("message", "상품 추가 중 오류가 발생했습니다.");
				}
				isUpdated = true;
				break;
			}
		}

		if (!isUpdated) {
			
			int insertRe = mDao.insertCart(cartItem);
			if (insertRe > 0) {
				response.put("status", "ok");
				response.put("message", "상품이 장바구니에 담겼습니다.");
			} else {
				response.put("status", "fail");
				response.put("message", "상품 추가 중 오류가 발생했습니다.");
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
		
		log.info("서비스 로그인proc" + loginUser);
		if (dbUser != null) {
			if (!dbUser.get("MEMBER_PASSWORD").equals(loginUser.get("password"))) {
				response.put("user",dbUser);
				response.put("message", "비밀번호가 다릅니다.");
			} else {
				response.put("user",dbUser);
				response.put("message", "환영합니다 "+dbUser.get("MEMBER_NAME")+"님!");
			}
		} else {
			response.put("user",dbUser);
			response.put("message", "가입된 아이디가 없습니다.");
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
			msg = "가입 완료";
			view = "/login";
		} else {
			msg = "가입 실패";
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
//		log.info("오늘= " + formattedDate);

		// 다른날짜 테스트용
//	        LocalDate today1 = LocalDate.now();
//	        LocalDate tomorrow = today1.plusDays(1);
//	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        String formattedDate1 = tomorrow.format(formatter1);
//	        log.info("내일= " + formattedDate1);
		int idRe = mDao.idCheck(member.getMemberId());
		if (idRe == 0) {
			log.info("로그인proc중 null");
			msg = "해당하는 아이디가 없습니다.";
			view = "redirect:/login";
		} else {
			member = mDao.login(member);
			if (member != null) {
				msg = "안녕하세요 " + member.getMemberName() + "님";
//				//주소 좌표로 변환
//				String[] coordinates = util.getAddressPoint(member.getAddress2());
//				log.info("응답"+coordinates);
//				String lat = coordinates[0];
//				String lng = coordinates[1];

				// 추출한 좌표값 사용
//				log.info("Latitude: " + lat + ", Longitude: " + lng);
				session.setAttribute("member", member);
				view = "redirect:/apiMain";
			} else {
				msg = "비밀번호가 틀렸습니다.";
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
