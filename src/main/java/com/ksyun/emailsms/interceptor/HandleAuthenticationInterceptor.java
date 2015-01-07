package com.ksyun.emailsms.interceptor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ksyun.emailsms.utils.Constants;
import com.ksyun.emailsms.utils.InitConst;


public class HandleAuthenticationInterceptor extends HandlerInterceptorAdapter {
	public static Map<String, Calendar> map = new HashMap<String, Calendar>();
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String host = request.getRemoteHost();
		Cookie cookie = getCookieByName(request, InitConst.BACKEND);
		Integer timeout = Integer.valueOf(Constants
				.getPropertyValue("cookie.timeout"));
		if (uri.indexOf("/login") > -1 || uri.indexOf("/html") > -1
			|| uri.indexOf("/js") > -1 || uri.indexOf("/img") > -1
			|| uri.indexOf("/css") > -1 || uri.indexOf("/font") > -1) {
			return true;
		}

		if (cookie != null) {
			String backend = cookie.getValue();
			if (backend != null) {
				Calendar now = Calendar.getInstance();
				Calendar time = map.get(backend);
				if (time == null) {
					response.sendRedirect("/login");
					return false;
				}
				if (now.getTimeInMillis() - time.getTimeInMillis() > timeout) {
					map.remove(backend);      
					response.sendRedirect("/login");
					return false;
				}
			} else {
				response.sendRedirect("/login");
				return false;
			}
		} else { 
			response.sendRedirect("/login");
			return false;
		}
		return true;	
	}
	
	
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
