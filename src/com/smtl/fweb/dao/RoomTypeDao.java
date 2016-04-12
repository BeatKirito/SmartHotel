package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.RoomType;

public interface RoomTypeDao {

	/**
	 * 根据类型ID获取房间类型实例
	 * @param typeID		类型ID
	 * @return	房间类型实例
	 */
	RoomType get(Integer typeID);
	
	/**
	 * 根据类型名获取类型实例
	 * @param typeName		类型名
	 * @return	房间类型实例
	 */
	RoomType getFormName(String typeName);
	
	/**
	 * 保存持久化的实例
	 * @param roomType	房间类型实例
	 * @return	房间类型ID
	 */
	Integer save(RoomType roomType);
	
	/**
	 * 更新持久化实例
	 * @param roomType	房间类型实例
	 */
	void update(RoomType roomType);
	
	/**
	 * 删除持久化实例
	 * @param roomType	房间类型实例
	 */
	void delete(RoomType roomType);
	
	/**
	 * 删除持久化实例
	 * @param typeID 房间类型ID
	 */
	void delete(Integer typeID);
	
	/**
	 * 获取所有房间类型实例
	 * @return	房间类型实例集合
	 */
	List<RoomType> findAll();
}
