package com.com.com.erp.dto;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class MemberDto {
	private String memberId;
	private String memberName;
	private String password;
	private String addrZipcode;
	private String address1;
	private String address2;
	private String memberRank;
	private String apperId;
	private String apperName;
	private String firstRank;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberRank() {
		return memberRank;
	}
	public void setMemberRank(String memberRank) {
		this.memberRank = memberRank;
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
	public String getFirstRank() {
		return firstRank;
	}
	public void setFirstRank(String firstRank) {
		this.firstRank = firstRank;
	}
	
	public String getAddrZipcode() {
		return addrZipcode;
	}
	public void setAddrZipcode(String addrZipcode) {
		this.addrZipcode = addrZipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberName=" + memberName + ", password=" + password
				+ ", addrZipcode=" + addrZipcode + ", address1=" + address1 + ", address2=" + address2 + ", memberRank="
				+ memberRank + ", apperId=" + apperId + ", apperName=" + apperName + ", firstRank=" + firstRank + "]";
	}
	
	
}
