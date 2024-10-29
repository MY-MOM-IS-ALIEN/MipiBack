package com.com.com.erp.dto;

public class SearchDto {
	private String category;
	private String keyword;
	private String apprStat;
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String startDate;
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String endDate;
	private String memberRank;
	private String memberId;
	private String memberName;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getApprStat() {
		return apprStat;
	}
	public void setApprStat(String apprStat) {
		this.apprStat = apprStat;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMemberRank() {
		return memberRank;
	}
	public void setMemberRank(String memberRank) {
		this.memberRank = memberRank;
	}
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
	@Override
	public String toString() {
		return "SearchDto [category=" + category + ", keyword=" + keyword + ", apprStat=" + apprStat + ", startDate="
				+ startDate + ", endDate=" + endDate + ", memberRank=" + memberRank + ", memberId=" + memberId
				+ ", memberName=" + memberName + "]";
	}
	
	
}
