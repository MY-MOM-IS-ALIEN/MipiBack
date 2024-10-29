package com.com.com.erp.dto;

public class ApiUserDto {
	private String apiId;
    private String apiPass;
    private String apiName;
    private String apiAddress1;
    private String apiAddress2;
    private String apiAddress3;
    private String apiEmail1;
    private String apiEmail2;
    private String apiPhone;
    
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiPass() {
		return apiPass;
	}
	public void setApiPass(String apiPass) {
		this.apiPass = apiPass;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiAddress1() {
		return apiAddress1;
	}
	public void setApiAddress1(String apiAddress1) {
		this.apiAddress1 = apiAddress1;
	}
	public String getApiAddress2() {
		return apiAddress2;
	}
	public void setApiAddress2(String apiAddress2) {
		this.apiAddress2 = apiAddress2;
	}
	public String getApiAddress3() {
		return apiAddress3;
	}
	public void setApiAddress3(String apiAddress3) {
		this.apiAddress3 = apiAddress3;
	}
	public String getApiEmail1() {
		return apiEmail1;
	}
	public void setApiEmail1(String apiEmail1) {
		this.apiEmail1 = apiEmail1;
	}
	public String getApiEmail2() {
		return apiEmail2;
	}
	public void setApiEmail2(String apiEmail2) {
		this.apiEmail2 = apiEmail2;
	}
	public String getApiPhone() {
		return apiPhone;
	}
	public void setApiPhone(String apiPhone) {
		this.apiPhone = apiPhone;
	}
	
	@Override
	public String toString() {
		return "apiUserDto [apiId=" + apiId + ", apiPass=" + apiPass + ", apiName=" + apiName + ", apiAddress1="
				+ apiAddress1 + ", apiAddress2=" + apiAddress2 + ", apiAddress3=" + apiAddress3 + ", apiEmail1="
				+ apiEmail1 + ", apiEmail2=" + apiEmail2 + ", apiPhone=" + apiPhone + "]";
	}
    
    
}
