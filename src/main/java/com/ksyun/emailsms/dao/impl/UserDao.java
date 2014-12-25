package com.ksyun.emailsms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ksyun.emailsms.dao.IUserDao;
import com.ksyun.emailsms.dto.UserDto;

@Repository
public class UserDao<UserDto> extends BaseDao<UserDto, Long> implements IUserDao<UserDto>{
	
}
