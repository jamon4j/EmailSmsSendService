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
import com.ksyun.emailsms.utils.InitConst;

/**
 * Author: zhangjiajiang
 * Date: 14-12-25
 * Func:调用邮件发送接口对目标用户发送邮件
 * 
 * 接口：
邮件发送接口
URL:http://10.0.2.145/mailop/sendpmail 
参数: { "body": "邮箱内容", "format": "html",//格式 "receiver": "邮箱", "sender": "金山云", "subject": "标题" } 
返回信息: { "state": "1" 1成功 } 
 */
@Service
public class EmailSendService implements IEmailSendService{
	@Autowired
    private JSONService jsonService;
	
	private static Logger logger = Logger.getLogger(IEmailSendService.class);
	
	public List<UserDto> sendEmail(List<UserDto> list,List<String> regions,
			List<Integer> products,String email_subject,String email_content)
	{
		/****************线下测试******************/
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
		/****************线下测试******************/
		
		EmailStatusPojo esp = null;
		int sendSuccess = 1;
		//调用邮件发送接口发送邮件
		for(UserDto user:list)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("body", email_content);
			map.put("format", "html");
			map.put("receiver", user.getEmail());
			map.put("sender", InitConst.EMAIL_SENDER);
			map.put("subject", email_subject);
			String requestBody = JSONObject.toJSONString(map);
			esp = jsonService.getPo(InitConst.EMAIL_URL, requestBody, HttpMethod.POST,EmailStatusPojo.class);
			if(esp.getState()!=1||esp == null)
			{
				logger.error("user_id:"+user.getUserId()+" Email:"+user.getEmail()+" 的邮件发送失败...");
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
