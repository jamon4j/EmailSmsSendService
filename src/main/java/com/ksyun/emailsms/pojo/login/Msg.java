package com.ksyun.emailsms.pojo.login;

import com.ksyun.emailsms.pojo.BasePo;

public class Msg extends BasePo{
	private String success;
    private String msg;
    private String url;
    private String cookie;
    
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	} 
}
