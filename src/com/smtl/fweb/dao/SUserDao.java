package com.smtl.fweb.dao;

import java.util.List;
import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.damain.SUserWT;;

public interface SUserDao {

	/**
	 * 根据用户获取用户实例
	 * @param userId		用户ID
	 * @return	用户实例
	 */
	SUser get(Integer userId);
	
	/**
	 * 根据用户名获取用户实例
	 * @param userName		用户名
	 * @return	用户实例
	 */
	SUserWT getFormName(String userName);
	
	/**
	 * 根据账号获取用户实例
	 * @param Account		账号
	 * @return	用户实例
	 */
	SUserWT getFormAccount(String Account);
	
	/**
	 * 保存持久化的实例
	 * @param user	User持久化对象
	 * @return	持久化对象标识属性（ID）
	 */
	Integer save(SUser user);
	
	/**
	 * 更新持久化实例
	 * @param user	User持久化实例
	 */
	void update(SUser user);
	
	/**
	 * 删除持久化实例
	 * @param user	User持久化实例
	 */
	void delete(SUser user);
	
	/**
	 * 根据持久化实例
	 * @param userId 用户持久化对象Id
	 */
	void delete(Integer userId);
	
	/**
	 * 获取所有用户实例
	 * @return	用户实例集合
	 */
	List<SUserWT> findAll();
	
	/**
	 * 根据系统用户类型ID获取用户实例集合
	 * @param typeID  用户类型ID
	 * @param typeName  用户类型名称
	 * @return	用户实例集合
	 */
	List<SUserWT> findWithParams(Integer typeID, String typeName);
	
	/**
	 * 根据用户名获取用户实例
	 * @param userName	用户名
	 * @return	相应用户实例
	 */
	//SUser findByUserName(String userName);
	
	/**
	 * 根据页码分页查询用信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @return	指定页用户信息集合
	 */
	//List<SUser> findByPage(final int offset,final int pagesize);
	
	/**
	 * 获取用户记录总数
	 * @return	返回用户记录总数
	 */
	//int getRecordNum();
}
