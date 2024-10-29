package com.com.com.erp.dao;

import com.com.com.erp.dto.ProxyDto;

public interface ProxyDao {

	int updateProxy(ProxyDto proxy);

	ProxyDto checkProxy(String memberId);

}
