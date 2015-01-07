package com.ksyun.emailsms.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ksyun.emailsms.pojo.BasePo;
import com.ksyun.emailsms.pojo.OpenStackResult;
import com.ksyun.emailsms.utils.HttpMethod;
import com.ksyun.emailsms.utils.HttpUtils;
/**
 * Author: zhangjiajiang
 * Date: 14-12-24
 * Func:openstack api 获取方法
 */
@Service
public class JSONService {
	 /**
     * 基本请求方法
     *
     */
    public <T extends BasePo> T getPo(String url,String requestBody,HttpMethod method,Class<T> clazz)
    {
    	OpenStackResult result;
        switch (method) {            
            case POST:
                result = HttpUtils.post(url,
                        setHeader(), requestBody);
                if (result != null && (result.getStatus() >= org.apache.http.HttpStatus.SC_OK)) {              
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
	    Map<String, String> header = new HashMap<String, String>();
	    header.put("Content-Type", "application/json");
	    return header;
    }

}
