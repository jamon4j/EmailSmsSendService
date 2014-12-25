package com.ksyun.emailsms.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础DAO接口
 * @param <T>  DTO
 * @param <PK> 主键
 */
public interface IBaseDao<T,PK> {
	/**
	 * 保存对象
	 * @param t
	 */
    void save(T t);

   /**
    * 根据主键删除对象
    * @param pk
    */
    void deleteById(PK pk);
    
    /**
     * 根据主键查询对象
     * @param pk
     * @return
     */
    T findById(PK pk);
    /**
     * 根据对象的值更新记录
     * @param t
     */
    void updateAll(T t);
    /**
     * 查询所有记录
     * @return
     */
    List<T> selectEmailMobile(Map<String, Object> params);
}
