package com.com.com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.com.com.erp.dto.ApiUserDto;
import com.com.com.erp.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDaoImpl implements MemberDao{
	@Autowired SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.com.com.erp.dao.MemberDao";
	
	//api사용한 회원가입
	public int apiJoinProc(ApiUserDto apiUser) {
		return sqlSession.insert(NAMESPACE + ".apiInsert",apiUser);
	}
	
	//리액트꺼
	public int updateCart(Map<String, Object> cartItem) {
	    return sqlSession.update(NAMESPACE + ".updateCart", cartItem);
	}
	
	//리액트꺼
	public int deleteCart(Map<String, Object> cartItem) {
	    return sqlSession.delete(NAMESPACE + ".deleteCart", cartItem);
	}
	
	//리액트꺼
		public List<Map<String, Object>> getCartList(){
			return sqlSession.selectList(NAMESPACE + ".getCartList");
		}
	
	public int insertCart(Map<String, Object> cartList) {
		log.info("디비가기 전"+cartList);
		return sqlSession.insert(NAMESPACE + ".insertCart",cartList);
	}
	
	//리액트꺼
	public int nextId() {
		return sqlSession.selectOne(NAMESPACE + ".nextId");
	}
	//리액트꺼
	public int joinProc(Map<String, Object>user) {
		return sqlSession.insert(NAMESPACE + ".joinProc",user);
	}
	//리액트꺼
	public Map<String, Object> reactLoginUser(Map<String, Object> loginUser) {
		return sqlSession.selectOne(NAMESPACE + ".reactLoginProc",loginUser);
	}
	//리액트꺼
	public List<Map<String, Object>> getPizzaList(){
		return sqlSession.selectList(NAMESPACE + ".getPizzaList");
	}
	
	public int idCheck(String memberId) {
		return sqlSession.selectOne(NAMESPACE + ".idCheck", memberId);
	}
	
	public MemberDto login(MemberDto member) {
		return sqlSession.selectOne(NAMESPACE + ".login", member);
	}
	
	
//	public int updateRank(MemberDto proxyMember) {
//		return sqlSession.update(NAMESPACE + ".updateRank", proxyMember);
//	}

}
