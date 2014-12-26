package com.ksyun.emailsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksyun.emailsms.dao.IUserDao;
import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.UserPojo;
import com.ksyun.emailsms.service.IEmailMobileGetService;

@Service
public class EmailMobileGetService implements IEmailMobileGetService{
	@Autowired
    private JSONService jsonService;
	@Autowired
	private IUserDao<UserDto> userDao;
	
	public List<UserDto> getUsersEmailMobile(List<String> regions,List<Integer> products,String sendMethod,String sms_content,String email_title,String email_content)
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("regions", regions);
		params.put("products", products);
		params.put("sendMethod", sendMethod);
		params.put("sms_content", sms_content);
		params.put("email_title", email_title);
		params.put("email_content", email_content);
		List<UserDto> emailMobileResult = userDao.selectEmailMobile(params);
		return emailMobileResult;
	}
}
