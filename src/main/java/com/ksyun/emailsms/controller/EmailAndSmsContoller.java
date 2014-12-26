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
import com.ksyun.emailsms.pojo.EmailStatusPojo;
import com.ksyun.emailsms.pojo.SmsStatusPojo;
import com.ksyun.emailsms.service.impl.EmailMobileGetService;
import com.ksyun.emailsms.service.impl.EmailSendService;
import com.ksyun.emailsms.service.impl.JSONService;
import com.ksyun.emailsms.service.impl.SmsSendService;
import com.ksyun.emailsms.utils.HttpMethod;

@Controller
public class EmailAndSmsContoller {
	@Autowired
	private EmailMobileGetService emailMobileGetService;
	
	@Autowired
	private EmailSendService emailSendService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired
	private JSONService jsonService;
		
	@RequestMapping(value = "/g/emailsms/sendEmailSms")
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
		list = emailMobileGetService.getUsersEmailMobile(regions,products,sendMethod,sms_content,email_subject,email_content);
		
		//根据发送方式发送信息
		EmailStatusPojo esp;
		SmsStatusPojo ssp;
		if(sendMethod.equals("email"))
		{
			esp = emailSendService.sendEmail(list, regions, products, email_subject, email_content);
		}
		else if(sendMethod.equals("sms"))
		{
			ssp = smsSendService.sendSms(list, regions, products, sms_content);
		}
		else if(sendMethod.equals("smsAndEmail"))
		{
			esp = emailSendService.sendEmail(list, regions, products, email_subject, email_content);
			ssp = smsSendService.sendSms(list, regions, products, sms_content);
		}
		
		mav.setViewName("/emailsms/queryResult");
		mav.addObject("userDtoList", list);//???
		return mav;
	}
}
