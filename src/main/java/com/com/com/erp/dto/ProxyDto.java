package com.com.com.erp.dto;

public class ProxyDto {
	private String proxyId;
	private String proxyName;
	private String apperId;
	private String apperName;
	private String proxyDate;
	private String proxyRank;
	
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	public String getApperId() {
		return apperId;
	}
	public void setApperId(String apperId) {
		this.apperId = apperId;
	}
	public String getApperName() {
		return apperName;
	}
	public void setApperName(String apperName) {
		this.apperName = apperName;
	}
	public String getProxyDate() {
		return proxyDate;
	}
	public void setProxyDate(String proxyDate) {
		this.proxyDate = proxyDate;
	}
	public String getProxyRank() {
		return proxyRank;
	}
	public void setProxyRank(String proxyRank) {
		this.proxyRank = proxyRank;
	}
	@Override
	public String toString() {
		return "ProxyDto [proxyId=" + proxyId + ", proxyName=" + proxyName + ", apperId=" + apperId + ", apperName="
				+ apperName + ", proxyDate=" + proxyDate + ", proxyRank=" + proxyRank + "]";
	}
	
	
	
}
