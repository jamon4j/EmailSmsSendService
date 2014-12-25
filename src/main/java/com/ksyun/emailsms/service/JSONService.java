package com.ksyun.emailsms.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.pojo.CallResult;
import com.ksyun.emailsms.utils.HttpMethod;
import com.ksyun.emailsms.utils.HttpUtils;

@Service
public class JSONService {
	 /**
     * 基本请求方法
     *
     */
    public <T> T getPo(String url,String requestBody,HttpMethod method,Class<T> clazz)
    {
    	CallResult result;
        switch (method) {            
            case POST:
                result = HttpUtils.post(url,
                        setHeader(), requestBody);
                if (result != null && (result.getStatus() >= org.apache.http.HttpStatus.SC_OK && result.getStatus() <= org.apache.http.HttpStatus.SC_MULTI_STATUS)) {              
                    return JSONObject.parseObject(result.getMessage(),clazz);
                } else {
                    // 错误日志入库              
                }
            case GET:break;   
            case DELETE:break;                 
            case PUT:break;
                
        }
        return null;
    }
    
    /**
     * 设置的header的map
     */
    private Map<String, String> setHeader() {
	    Map<String, String> header = new HashMap<>();
	    header.put("Content-Type", "application/json");
	    return header;
    }

}
