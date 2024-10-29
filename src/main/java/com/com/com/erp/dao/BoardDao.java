package com.com.com.erp.dao;

import java.util.List;
import java.util.Map;

import com.com.com.erp.dto.BoardDto;
import com.com.com.erp.dto.FileDto;
import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.dto.NBoard;
import com.com.com.erp.dto.SearchDto;

public interface BoardDao {

	int checkSeq();

	int insert(BoardDto board);

	int checkHiNum(int seq);
	
	List<BoardDto> getBoardList(int seq);

	List<BoardDto> selectBoardList(MemberDto member);

	int updateStat(BoardDto board);

	int updateBoard(BoardDto board);

	List<SearchDto> search(SearchDto search);

	int saveCheck(int seq);

	int updateStatProxy(BoardDto board);
	
	int fileSeqCheck();

	int insertFile(FileDto dfd);

	List<FileDto> getImageList(int seq);

	List<Map<String, Object>> getReactList();
	//≥ÿªÁ≈©∑Œ
	 List<NBoard> list(NBoard nboard);
	 
	    List<NBoard> searchBoard(Map<String, Object> searchParams); 
	
}
