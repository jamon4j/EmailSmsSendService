package com.ksyun.emailsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.EmailStatusPojo;
import com.ksyun.emailsms.service.IEmailMobileGetService;
import com.ksyun.emailsms.service.IEmailSendService;
import com.ksyun.emailsms.utils.HttpMethod;

@Service
public class EmailSendService implements IEmailSendService{
	@Autowired
    private JSONService jsonService;
	private static final String EMAIL_URL = "http://10.0.2.145/mailop/sendpmail";
	
	private static Logger logger = Logger.getLogger(IEmailSendService.class);
	
	public EmailStatusPojo sendEmail(List<UserDto> list,List<String> regions,
			List<Integer> products,String email_subject,String email_content)
	{
		/****************测试******************/
		int count=1;
		for(UserDto ud:list)
		{
			if(count%2==1)
			{
				ud.setEmail("bupt_zjj@163.com");
				ud.setMobile("18911918549");
			}
			else
			{
				ud.setEmail("372765149@qq.com");
				ud.setMobile("13121228840");
			}
			count++;
		}
		/****************测试******************/
		
		EmailStatusPojo esp = null;
		int sendSuccess = 1;
		//调用邮件发送接口发送邮件
		for(UserDto user:list)
		{
			Map<String,String> map = new HashMap<>();
			map.put("body", email_content);
			map.put("format", "html");
			map.put("receiver", user.getEmail());
			map.put("sender", "金山云");
			map.put("subject", email_subject);
			String requestBody = JSONObject.toJSONString(map);
			esp = jsonService.getPo(EMAIL_URL, requestBody, HttpMethod.POST,EmailStatusPojo.class);
			if(esp.getState()!=1||esp == null)
			{
				logger.error("user_id:"+user.getUserId()+" Email:"+user.getEmail()+" 的邮件发送失败...");
				sendSuccess = 0;
			}
		}
		esp.setState(sendSuccess);
		return esp;		
	}	
}
