package com.ksyun.emailsms.utils;

public class Constants {
	public static final Long DEFAULT_PAGEINDEX = 1L;
	public static final Integer DEFAULT_PAGESIZE = 10;

	private static PropertiesAccessor propertiesAccessor = AccessorFactory.createPropertiesAccessor(new String[]{"conf.properties"});

	public static String getPropertyValue(String key) {
		return propertiesAccessor.getValue(key);
	}

	public static String getPropertyValue(String key, Object... arguments) {
		return propertiesAccessor.getValue(key, arguments);
	}
	
	
	public static void setPropertyValue(String key, String value){
		propertiesAccessor.setValue(key, value);
	}
}
