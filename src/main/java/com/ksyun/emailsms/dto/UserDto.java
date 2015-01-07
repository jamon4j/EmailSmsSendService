package com.ksyun.emailsms.dto;

public class UserDto implements DtoInterface{
	//用户id
	private long userId;
	//用户email
	private String email;
	//用户mobile
	private String mobile;
	//信息发送状态
	private int status;
		
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toJson() {
		return null;
	}
}
