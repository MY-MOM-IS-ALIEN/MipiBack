package com.com.com.erp.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.com.com.erp.dto.ProxyDto;

@Repository
public class ProxyDaoImpl implements ProxyDao {
	@Autowired SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.com.com.erp.dao.ProxyDao";
	
	public int updateProxy(ProxyDto proxy) {
		return sqlSession.update(NAMESPACE + ".updateProxy", proxy);
	}
	
	public ProxyDto checkProxy(String memberId) {
		return sqlSession.selectOne(NAMESPACE + ".checkProxy", memberId);
	}
}
