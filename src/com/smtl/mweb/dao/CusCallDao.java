package com.smtl.mweb.dao;

import java.util.List;

import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.domain.CusCallWO;

public interface CusCallDao {

	/**
	 * 根据客户呼叫ID获取客户呼叫实例
	 * @param callID		客户呼叫ID
	 * @return	客户呼叫实例
	 */
	CusCall get(Integer callID);
	
	/**
	 * 根据客户呼叫ID获取客户呼叫实例
	 * @param callID		客户呼叫ID
	 * @return	客户呼叫实例
	 */
	CusCallWO getWO(Integer callID);
	
	/**
	 * 保存持久化的实例
	 * @param cusCall	客户呼叫实例
	 * @return	客户呼叫ID
	 */
	Integer save(CusCall cusCall);
	
	/**
	 * 更新持久化实例
	 * @param cusCall	客户呼叫实例
	 */
	void update(CusCall cusCall);
	
	/**
	 * 删除持久化实例
	 * @param cusCall	客户呼叫实例
	 */
	void delete(CusCall cusCall);
	
	/**
	 * 根据客户呼叫ID删除客户呼叫记录
	 * @param callID 客户呼叫ID
	 */
	void delete(Integer callID);
	
	/**
	 * 获取所有客户呼叫实例
	 * @return	客户呼叫实例集合
	 */
	List<CusCallWO> findAll();
	
	/**
	 * 根据页码以及其他查询参数分页查询信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @param suserTypeID	工作人员职务ID
	 * @param roomID		房间ID
	 * @param roomNum	房号
	 * @param state			当前状态
	 * @param sUserID 		受理人的ID
	 * @return	指定页信息集合
	 */
	List<CusCallWO> findByPage(final int offset,final int pagesize,Integer suserTypeID,
			Integer roomID, String roomNum, Integer state, Integer sUserID);
	
	/**
	 * 获取记录总数
	 * @param suserTypeID	工作人员职务ID
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 *@param state			当前状态
	 *@param sUserID 		受理人的ID
	 * @return	返回记录总数
	 */
	int getRecordNum(Integer suserTypeID,Integer roomID, 
			String roomNum, Integer state,Integer sUserID);
}
