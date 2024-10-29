package com.com.com.erp.dto;

public class FileDto {
	private int fileSeq;
	private String realName;
	private String saveName;
	private String regDate;
	private String savePath;
	private int listSeq;
	
	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}
	public void setListSeq(int listSeq) {
		this.listSeq = listSeq;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	@Override
	public String toString() {
		return "FileDto [fileSeq=" + fileSeq + ", realName=" + realName + ", saveName=" + saveName + ", regDate="
				+ regDate + ", savePath=" + savePath + ", listSeq=" + listSeq + "]";
	}
}
