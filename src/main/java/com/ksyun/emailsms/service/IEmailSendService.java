package com.ksyun.emailsms.service;

import java.util.List;
import java.util.Map;

import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.EmailStatusPojo;

public interface IEmailSendService {
	
	public List<UserDto> sendEmail(List<UserDto> list,List<String> regions,
			List<Integer> products,String email_subject,String email_content);
	
}
