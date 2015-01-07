package com.ksyun.emailsms.service;

import java.util.List;

import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.SmsStatusPojo;

public interface ISmsSendService {
	
	public List<UserDto> sendSms(List<UserDto> list,List<String> regions,
			List<Integer> products,String sms_content);
	
}
