package com.ksyun.emailsms.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.interceptor.HandleAuthenticationInterceptor;
import com.ksyun.emailsms.pojo.login.Msg;
import com.ksyun.emailsms.service.impl.LoginService;
import com.ksyun.emailsms.utils.SHA1;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, ModelAndView mav) {
		mav.setViewName("/emailsms/login");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mav) {
		boolean isSuccess = loginService.getUser(request.getParameter("username"), request.getParameter("password"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Msg msg = new Msg();
		if(isSuccess) {
			msg.setMsg("登陆成功");
			msg.setSuccess("true");
			msg.setUrl("/g/");
			// 设置Backend Cookie
			String cookie = SHA1
					.getDigestOfString((username + "{" + password + "}")
							.getBytes());
			HandleAuthenticationInterceptor.map.put(cookie,
					Calendar.getInstance());
			msg.setCookie(cookie);
		}
		else {
			msg.setMsg("登陆失败");
			msg.setSuccess("false");
			msg.setUrl("/login");	
		}
		return JSONObject.toJSONString(msg);
	}
}
