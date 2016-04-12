package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.Room;
import com.smtl.fweb.damain.RoomWT;

public interface RoomDao {

	/**
	 * 根据用户获取用户实例
	 * @param roomID		房间ID
	 * @return	房间实例
	 */
	Room get(Integer roomID);
	
	/**
	 * 根据房间号获取用户实例
	 * @param roomNum		房间号
	 * @return	房间实例
	 */
	RoomWT getFormRoomNum(String roomNum);
	
	/**
	 * 保存持久化的实例
	 * @param room	房间持久化对象
	 * @return	持久化对象标识属性（ID）
	 */
	Integer save(Room room);
	
	/**
	 * 更新持久化实例
	 * @param room	房间持久化实例
	 */
	void update(Room room);
	
	/**
	 * 删除持久化实例
	 * @param room	房间持久化实例
	 */
	void delete(Room room);
	
	/**
	 * 根据持久化实例
	 * @param roomID 房间ID
	 */
	void delete(Integer roomID);
	
	/**
	 * 获取所有房间实例
	 * @return	房间实例集合
	 */
	List<RoomWT> findAll();
	
	/**
	 * 根据页码以及其他查询参数分页查询信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @param floor		楼层号
	 * @param state		当前状态（是否已入住）
	 * @param typeID	房间类型ID
	 * @param typeName	房间类型名称
	 * @return	指定页信息集合
	 */
	List<RoomWT> findByPage(final int offset,final int pagesize,Integer floor,Boolean state, Integer typeID, String typeName);
	
	/**
	 * 获取记录总数
	 * @param floor		楼层号
	 * @param state		当前状态（是否已入住）
	 * @param typeName	房间类型名称
	 * @return	返回记录总数
	 */
	int getRecordNum(Integer floor,Boolean state, Integer typeID, String typeName);
	
}
