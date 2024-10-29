package com.com.com.erp.dao;

import java.util.List;
import java.util.Map;

import com.com.com.erp.dto.ApiUserDto;
import com.com.com.erp.dto.MemberDto;

public interface MemberDao {

	int idCheck(String memberId);

	MemberDto login(MemberDto member);

	int joinProc(Map<String, Object> user);
	
	int nextId();

	Map<String, Object> reactLoginUser(Map<String, Object> loginUser);

	List<Map<String, Object>> getPizzaList();

	int insertCart(Map<String, Object> cartList);

	List<Map<String, Object>> getCartList();
	
	int updateCart(Map<String, Object> cartItem);

	int deleteCart(Map<String, Object> cartList);
	
	int apiJoinProc(ApiUserDto apiUser);

//	int updateRank(MemberDto proxyMember);
}
