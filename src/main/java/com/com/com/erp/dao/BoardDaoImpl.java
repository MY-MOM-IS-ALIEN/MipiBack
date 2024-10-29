package com.com.com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.com.com.erp.dto.BoardDto;
import com.com.com.erp.dto.FileDto;
import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.dto.NBoard;
import com.com.com.erp.dto.SearchDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	@Autowired SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.com.com.erp.dao.BoardDao";
	
	public int checkSeq() {
		return sqlSession.selectOne(NAMESPACE + ".checkSeq");
	}
	
	public int fileSeqCheck() {
		return sqlSession.selectOne(NAMESPACE + ".fileSeqCheck");
	}
	
	public int insert(BoardDto board) {
		return sqlSession.insert(NAMESPACE + ".insert",board);
	}
	
	public int checkHiNum(int seq) {
		return sqlSession.selectOne(NAMESPACE + ".checkHiNum",seq);
	}
	
	//인서트하고 난 후 정보 찾아오는용
	public List<BoardDto> getBoardList(int seq) {
		return sqlSession.selectList(NAMESPACE + ".getBoardList",seq);
	}
	
	//메인갈때 본인글만 들고 이동
	public List<BoardDto> selectBoardList(MemberDto member) {
		return sqlSession.selectList(NAMESPACE + ".selectBoardList",member);
	}
	
	public int updateStat(BoardDto board) {
		return sqlSession.update(NAMESPACE + ".updateStat",board);
	}
	
	public int updateBoard(BoardDto board) {
		return sqlSession.update(NAMESPACE + ".updateBoard",board);
	}
	
	public List<SearchDto> search(SearchDto search) {
		return sqlSession.selectList(NAMESPACE + ".search",search);
	}
	
	public int saveCheck(int seq) {
		return sqlSession.selectOne(NAMESPACE + ".saveCheck",seq);
	}
	
	public int updateStatProxy(BoardDto board) {
		return sqlSession.update(NAMESPACE + ".updateStatProxy",board);
	}
	
	public int insertFile(FileDto dfd) {
		return sqlSession.insert(NAMESPACE + ".insertFile",dfd);
	}
	
	public List<FileDto> getImageList(int seq) {
		return sqlSession.selectList(NAMESPACE + ".getImageList",seq);
	}
	
	public List<Map<String, Object>> getReactList() {
		return sqlSession.selectList(NAMESPACE + ".getReactList");
	}
	
	 public List<NBoard> list(NBoard nboard) {
	      
	        List<NBoard> list = sqlSession.selectList(NAMESPACE + ".NboardList", nboard);
	       // Log.info("쿼리결과 : " + list); // 쿼리 결과를 로그로 출력
	        return list;
	    }
	    
	    public List<NBoard> searchBoard(Map<String, Object> searchParams) {
	        
	        return sqlSession.selectList(NAMESPACE + ".searchBoard", searchParams);
	    }

}
