package com.ksyun.emailsms.service;

import java.util.List;

import com.ksyun.emailsms.dto.UserDto;

public interface IEmailMobileGetService {

	public List<UserDto> getUsersEmailMobile(List<String> regions,List<Integer> products,String sendMethod,
			String sms_content,String email_title,String email_content);
	
}
