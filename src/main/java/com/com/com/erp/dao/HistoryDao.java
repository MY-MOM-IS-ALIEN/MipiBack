package com.com.com.erp.dao;

import java.util.List;

import com.com.com.erp.dto.HistoryDto;

public interface HistoryDao {

		int insertHistory(HistoryDto history);
		
		List<HistoryDto> getHistoryList(int seq);
}
