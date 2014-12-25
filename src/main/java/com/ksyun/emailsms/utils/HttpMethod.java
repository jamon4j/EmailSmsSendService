package com.ksyun.emailsms.utils;


public enum HttpMethod {
	
	PUT("PUT"),
    DELETE("DELETE"),
    POST("POST"),
    GET("GET");
    private HttpMethod(String action) {
        this.action = action;
    }

    private String action;

    public String value() {
        return action;
    }
}
