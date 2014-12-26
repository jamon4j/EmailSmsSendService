package com.ksyun.emailsms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.SmsStatusPojo;
import com.ksyun.emailsms.service.IEmailSendService;
import com.ksyun.emailsms.service.ISmsSendService;
import com.ksyun.emailsms.utils.HttpMethod;

@Service
public class SmsSendService implements ISmsSendService{
	@Autowired
    private JSONService jsonService;
	private static final String SMS_URL = "http://10.0.7.1/sms/sendSms";
	private static Logger logger = Logger.getLogger(ISmsSendService.class);
	
	public SmsStatusPojo sendSms(List<UserDto> list,List<String> regions,
			List<Integer> products,String sms_content)
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
		
		SmsStatusPojo ssp = null;
		//调用短信发送接口发送短信
		for(UserDto user:list)
		{
			Map<String,String> map = new HashMap<>();
			map.put("desNo", user.getMobile());
			map.put("msg", sms_content);
			String requestBody = JSONObject.toJSONString(map);
			ssp = jsonService.getPo(SMS_URL, requestBody, HttpMethod.POST,SmsStatusPojo.class);
			if(ssp == null)
			{
				logger.error("user_id:"+user.getUserId()+" Mobile:"+user.getMobile()+" 的短信发送失败...");
			}
		}
		return ssp;
	}
}
