package com.ksyun.emailsms.dao.impl;


import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.ksyun.emailsms.dao.IBaseDao;

/**
 * 基础DAO类 ，（提供基本的方法，由于mybatis需要有配置文件mapper ID对应，所以在mapper文件中还是要有相应的insert deleteById findById, findALL配置）
 * @param <T>  DTO对象
 * @param <PK> 主键类型
 */
public abstract class BaseDao<T, PK> implements IBaseDao<T, PK>{
	@Resource
	protected SqlSession sqlSession;
	
	protected String nameSpace;
	
	{
        //由于泛型擦除规则，暂时只能以此方式获取，由于不能类型转换为Class<T>,只能取得simpleName。留待以后更好的解决办法（2013-08-15 ）
    	String t_name = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();   
        nameSpace = String.format("com.ksyun.emailsms.dto.%s", t_name);
    }
	
	@Override
	public List<T> selectEmailMobile(Map<String, Object> params) {
		return sqlSession.selectList(String.format("%s.selectEmailMobile", nameSpace), params);		
	}
	
	@Override
	public T findById(PK pk) {
		return sqlSession.selectOne(String.format("%s.findById", nameSpace), pk);
	}
	
	@Override
	public void save(T t) {
		sqlSession.insert(String.format("%s.insert", nameSpace), t);
	}

	@Override
	public void deleteById(PK pk) {
		sqlSession.delete(String.format("%s.deleteById", nameSpace), pk);
	}

	@Override
	public void updateAll(T t){
		sqlSession.update(String.format("%s.updateAll", nameSpace), t);
	}
	
}
