package com.com.com.erp.dto;

import java.time.LocalDate;

public class HistoryDto {
	private int hiNum;
	private LocalDate signDate;
	private String approval;
	private String memberName;
	private String signStatus;
	private int hiNo;
	
	public int getHiNum() {
		return hiNum;
	}
	public void setHiNum(int hiNum) {
		this.hiNum = hiNum;
	}
	public LocalDate getSignDate() {
		return signDate;
	}
	public void setSignDate(LocalDate signDate) {
		this.signDate = signDate;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public int getHiNo() {
		return hiNo;
	}
	public void setHiNo(int hiNo) {
		this.hiNo = hiNo;
	}
	@Override
	public String toString() {
		return "HistoryDto [hiNum=" + hiNum + ", signDate=" + signDate + ", approval=" + approval + ", memberName="
				+ memberName + ", signStatus=" + signStatus + ", hiNo=" + hiNo + "]";
	}
	
	

}
