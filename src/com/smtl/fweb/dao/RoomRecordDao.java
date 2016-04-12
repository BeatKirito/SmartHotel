package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.RoomRecord;
import com.smtl.fweb.damain.RoomRecordWN;

public interface RoomRecordDao {

	/**
	 * 根据入住记录ID获取入住记录实例
	 * @param recordID		入住记录ID
	 * @return	入住记录实例
	 */
	RoomRecord get(Integer recordID);
	
	/**
	 * 根据身份证卡号获取顾客的入住记录
	 * @param cardNum		顾客的身份证号码
	 * @return
	 */
	List<RoomRecord> getFormCardNum(String cardNum);
	
	/**
	 * 保存持久化的实例
	 * @param roomRecord	入住记录实例
	 * @return	入住记录ID
	 */
	Integer save(RoomRecord roomRecord);
	
	/**
	 * 更新持久化实例
	 * @param roomRecord	入住记录实例
	 */
	void update(RoomRecord roomRecord);
	
	/**
	 * 删除持久化实例
	 * @param roomRecord	入住记录实例
	 */
	void delete(RoomRecord roomRecord);
	
	/**
	 * 根据入住记录ID删除预订订单记录
	 * @param recordID 入住记录ID
	 */
	void delete(Integer recordID);
	
	/**
	 * 获取所有入住记录实例
	 * @return	入住记录实例集合
	 */
	List<RoomRecordWN> findAll();
	
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
	List<RoomRecordWN> findByPage(final int offset,final int pagesize,Boolean isAvailable,
			String cusName, Integer roomID, String roomNum);
	
	/**
	 * 获取记录总数
	 * @param isAvailable	是否有效
	 * @param cusName	顾客姓名
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 * @return	返回记录总数
	 */
	int getRecordNum(Boolean isAvailable, String cusName, 
			Integer roomID, String roomNum);
}
