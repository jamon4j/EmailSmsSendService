package com.ksyun.emailsms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean getUser(String username, String password) {
		if(username.equals("ksc@kingsoft.com")&&password.equals("hk1997"))
			return true;
		else 
			return false;
	}
}
