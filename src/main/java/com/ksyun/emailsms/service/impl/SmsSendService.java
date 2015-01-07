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
import com.ksyun.emailsms.utils.InitConst;

/**
 * Author: zhangjiajiang
 * Date: 14-12-25
 * Func:调用短信发送接口对目标用户发送短信
 * 
 * 接口：
短信：url：http://10.0.7.1/sms/sendSms
参数：{"desNo":"13811310419","msg":"内容 [金山云]"}
result：{"batchnumber":"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<string xmlns=\"http://tempuri.org/\">231403341930771298</string>"}
 */
@Service
public class SmsSendService implements ISmsSendService{
	@Autowired
    private JSONService jsonService;
	private static Logger logger = Logger.getLogger(ISmsSendService.class);
	
	public List<UserDto> sendSms(List<UserDto> list,List<String> regions,
			List<Integer> products,String sms_content)
	{
		/****************线下测试*******************/
		/*int count=1;
		for(UserDto ud:list)
		{
			if(count%2==1)
			{
				ud.setEmail("zhangjiajiang@kingsoft.com");
				ud.setMobile("18911918549");
			}
			else
			{
				ud.setEmail("372765149@qq.com");
				ud.setMobile("18911918549");
			}
			count++;
		}*/
		/****************线下测试*******************/
		
		SmsStatusPojo ssp = null;
		//调用短信发送接口发送短信
		for(UserDto user:list)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("desNo", user.getMobile());
			map.put("msg", sms_content);
			String requestBody = JSONObject.toJSONString(map);
			ssp = jsonService.getPo(InitConst.SMS_URL, requestBody, HttpMethod.POST,SmsStatusPojo.class);
			if(ssp == null)
			{
				logger.error("user_id:"+user.getUserId()+" Mobile:"+user.getMobile()+" 的短信发送失败...");
				list.get(list.indexOf(user)).setStatus(0);
			}
			else
			{
				list.get(list.indexOf(user)).setStatus(1);
			}
		}
		return list;
	}
}
