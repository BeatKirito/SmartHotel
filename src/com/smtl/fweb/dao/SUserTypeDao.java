package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.SUserType;

public interface SUserTypeDao {

	/**
	 * 根据类型ID获取类型数据
	 * @param userId		类型ID
	 * @return	类型实例
	 */
	SUserType get(Integer typeID);
	
	/**
	 * 根据类型名获取用户实例
	 * @param typeName		类型名
	 * @return	类型实例
	 */
	SUserType getFormName(String typeName);
	
	/**
	 * 保存持久化的实例
	 * @param userType	类型持久化对象
	 * @return	持久化对象标识属性（ID）
	 */
	Integer save(SUserType userType);
	
	/**
	 * 更新持久化实例
	 * @param userType	类型持久化对象
	 */
	void update(SUserType userType);
	
	/**
	 * 删除持久化实例
	 * @param userType	类型持久化对象
	 */
	void delete(SUserType userType);
	
	/**
	 * 根据持久化实例
	 * @param typeID 类型ID
	 */
	void delete(Integer typeID);
	
	/**
	 * 获取所有类型实例
	 * @return	类型实例集合
	 */
	List<SUserType> findAll();
}
