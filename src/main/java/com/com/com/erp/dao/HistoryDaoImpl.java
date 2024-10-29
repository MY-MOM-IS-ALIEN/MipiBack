package com.com.com.erp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.com.com.erp.dto.HistoryDto;

@Repository
public class HistoryDaoImpl implements HistoryDao {
	@Autowired SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.com.com.erp.dao.HistoryDao";
	
	public int insertHistory(HistoryDto history) {
		return sqlSession.insert(NAMESPACE + ".insertHistory",history);
	}
	
	public List<HistoryDto> getHistoryList(int seq) {
		return sqlSession.selectList(NAMESPACE + ".getHistoryList",seq);
	}
}
