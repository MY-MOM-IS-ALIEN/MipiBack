package com.com.com.erp.dto;

public class BoardDto {
	private int seq;
	private String memberId;
	private String memberName;
	private String boardTitle;
	private String boardContent;
	private String regDate;
	private String apprDate;
	private String approver;
	private String apprStat;
	private String memberRank;
	
	public String getFormattedRegDate() {
        if (regDate != null && !regDate.isEmpty()) {
            return regDate.substring(0, 10); // "yyyy/MM/dd" 형식으로 자름
        }
        return regDate; // null 또는 빈 문자열이면 그대로 반환
    }

    // apprDate를 "yyyy/MM/dd" 형식으로 자르는 메서드
    public String getFormattedApprDate() {
        if (apprDate != null && !apprDate.isEmpty()) {
            return apprDate.substring(0, 10); // "yyyy/MM/dd" 형식으로 자름
        }
        return apprDate; // null 또는 빈 문자열이면 그대로 반환
    }
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprStat() {
		return apprStat;
	}
	public void setApprStat(String apprStat) {
		this.apprStat = apprStat;
	}
	public String getMemberRank() {
		return memberRank;
	}

	public void setMemberRank(String memberRank) {
		this.memberRank = memberRank;
	}
	@Override
	public String toString() {
		return "BoardDto [seq=" + seq + ", memberId=" + memberId + ", memberName=" + memberName + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", regDate=" + regDate + ", apprDate=" + apprDate
				+ ", approver=" + approver + ", apprStat=" + apprStat + ", memberRank=" + memberRank + "]";
	}
}
