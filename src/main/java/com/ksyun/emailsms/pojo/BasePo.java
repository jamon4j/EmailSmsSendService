package com.ksyun.emailsms.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BasePo {
	private String primaryKey;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);	
	}
}
