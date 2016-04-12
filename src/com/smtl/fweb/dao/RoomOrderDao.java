package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.RoomOrder;
import com.smtl.fweb.damain.RoomOrderWN;

public interface RoomOrderDao {

	/**
	 * 根据预订订单ID获取预订订单实例
	 * @param orderID		预订订单ID
	 * @return	预订订单实例
	 */
	RoomOrder get(Integer orderID);
	
	/**
	 * 保存持久化的实例
	 * @param roomOrder	预订订单实例
	 * @return	预订订单ID
	 */
	Integer save(RoomOrder roomOrder);
	
	/**
	 * 更新持久化实例
	 * @param roomOrder	预订订单实例
	 */
	void update(RoomOrder roomOrder);
	
	/**
	 * 删除持久化实例
	 * @param roomOrder	预订订单实例
	 */
	void delete(RoomOrder roomOrder);
	
	/**
	 * 根据预订订单ID删除预订订单记录
	 * @param orderID 预订订单ID
	 */
	void delete(Integer orderID);
	
	/**
	 * 获取所有预订订单实例实例
	 * @return	预订订单实例实例集合
	 */
	List<RoomOrderWN> findAll();
	
	/**
	 * 根据页码以及其他查询参数分页查询信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @param isAvailable		是否有效
	 * @param cusName	顾客姓名
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 * @return	指定页信息集合
	 */
	List<RoomOrderWN> findByPage(final int offset,final int pagesize,Boolean isAvailable,
			String cusName, Integer roomID, String roomNum);
	
	/**
	 * 获取记录总数
	 * @param isAvailable		是否有效
	 * @param cusName	顾客姓名
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 * @return	返回记录总数
	 */
	int getRecordNum(Boolean isAvailable, String cusName, 
			Integer roomID, String roomNum);
}
