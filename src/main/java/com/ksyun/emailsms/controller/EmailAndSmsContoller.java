package com.ksyun.emailsms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.dto.UserDto;
import com.ksyun.emailsms.pojo.CallResult;
import com.ksyun.emailsms.service.EmailAndSmsService;
import com.ksyun.emailsms.service.JSONService;
import com.ksyun.emailsms.utils.HttpMethod;

@Controller
public class EmailAndSmsContoller {
	@Autowired
	private EmailAndSmsService emailAndSmsService;
	
	@Autowired
	private JSONService jsonService;
	private static final String EMAIL_URL = "http://10.0.2.145/mailop/sendpmail";
	private static final String SMS_URL = "http://10.0.7.1/sms/sendSms";
	
	@RequestMapping(value = "/g/emailsms/getEmailSms")
	public ModelAndView findUserEmailMobile(@RequestParam("region_checkbox") String[] region,@RequestParam("product_checkbox") String[] product,
			@RequestParam("sendMethod") String sendMethod,@RequestParam("sms_content") String sms_content,@RequestParam("email_subject") String email_subject,
			@RequestParam("email_content") String email_content,ModelAndView mav)
	{	
		List<String> regions = new ArrayList<String>();
		List<Integer> products = new ArrayList<Integer>();
		
		for(int i=0;i<region.length;i++)
		{
			regions.add(region[i]);
		}
		
		String tempStr = new String();
		for(int i=0;i<product.length;i++)
		{
			if(i==product.length-1) tempStr += product[i];
			else tempStr += product[i] + ",";
		}
		
		String[] tempArr = tempStr.split(",");
		for(int i=0;i<tempArr.length;i++)
		{
			products.add(Integer.valueOf(tempArr[i]));
		}
		
		//获取指定用户的email和mobile
		List<UserDto> list = new ArrayList<>();
		list = emailAndSmsService.getUsersEmailSms(regions,products,sendMethod,sms_content,email_subject,email_content);
		mav.addObject("userDtoList", list);
		
		/*测试接口*/
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
		
		//调用邮件发送接口发送邮件
		if(sendMethod.equals("email"))
		{
			for(UserDto user:list)
			{
				Map<String,String> map = new HashMap<>();
				map.put("body", email_content);
				map.put("format", "html");
				map.put("receiver", user.getEmail());
				map.put("sender", "金山云");
				map.put("subject", email_subject);
				String requestBody = JSONObject.toJSONString(map);
				CallResult cr = jsonService.getPo(EMAIL_URL, requestBody, HttpMethod.POST,CallResult.class);
				System.out.println("Result from mailop: status:"+cr.getStatus()+" message:"+cr.getMessage());
			}
		}
		//调用短信发送接口发送短信
		else if(sendMethod.equals("sms"))
		{
			for(UserDto user:list)
			{
				Map<String,String> map = new HashMap<>();
				map.put("desNo", user.getMobile());
				map.put("msg", sms_content);
				String requestBody = JSONObject.toJSONString(map);
				CallResult cr = jsonService.getPo(SMS_URL, requestBody, HttpMethod.POST,CallResult.class);
				System.out.println("Result from mailop: status:"+cr.getStatus()+" message:"+cr.getMessage());
			}
		}
		//同时发送
		else if(sendMethod.equals("smsAndEmail"))
		{
			for(UserDto user:list)
			{
				Map<String,String> map = new HashMap<>();
				map.put("desNo", user.getMobile());
				map.put("msg", sms_content);
				String requestBody = JSONObject.toJSONString(map);
				CallResult cr = jsonService.getPo(SMS_URL, requestBody, HttpMethod.POST,CallResult.class);
				//System.out.println("Result from mailop: status:"+cr.getStatus()+" message:"+cr.getMessage());
			}
			for(UserDto user:list)
			{
				Map<String,String> map = new HashMap<>();
				map.put("body", email_content);
				map.put("format", "html");
				map.put("receiver", user.getEmail());
				map.put("sender", "金山云");
				map.put("subject", email_subject);
				String requestBody = JSONObject.toJSONString(map);
				CallResult cr = jsonService.getPo(EMAIL_URL, requestBody, HttpMethod.POST,CallResult.class);
				//System.out.println("Result from mailop: status:"+cr.getStatus()+" message:"+cr.getMessage());
			}
		}
		return mav;
	}
}
