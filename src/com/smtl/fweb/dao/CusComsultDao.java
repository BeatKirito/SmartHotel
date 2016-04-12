package com.smtl.fweb.dao;

import java.util.Date;
import java.util.List;

import com.smtl.fweb.damain.CusConsult;
import com.smtl.fweb.damain.CusConsultWN;

public interface CusComsultDao {

	/**
	 * 根据咨询记录ID获取咨询记录实例
	 * @param consultID		咨询记录ID
	 * @return	咨询记录实例
	 */
	CusConsult get(Integer consultID);
	
	/**
	 * 保存持久化的实例
	 * @param cusConsult	咨询记录实例
	 * @return	咨询记录ID
	 */
	Integer save(CusConsult cusConsult);
	
	/**
	 * 更新持久化实例
	 * @param cusConsult	咨询记录实例
	 */
	void update(CusConsult cusConsult);
	
	/**
	 * 删除持久化实例
	 * @param cusConsult	咨询记录实例
	 */
	void delete(CusConsult cusConsult);
	
	/**
	 * 根据咨询记录ID删除预订订单记录
	 * @param consultID 咨询记录ID
	 */
	void delete(Integer consultID);
	
	/**
	 * 获取所有咨询记录实例
	 * @return	咨询记录实例集合
	 */
	List<CusConsultWN> findAll();
	
	/**
	 * 获取所有未回复咨询记录实例
	 * @return	咨询记录实例集合
	 */
	List<CusConsultWN> findAllNoReply();
	
	/**
	 * 根据页码以及其他查询参数分页查询信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @param isReply			是否已回复
	 * @param consultTime	咨询时间
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 * @return	指定页信息集合
	 */
	List<CusConsultWN> findByPage(final int offset,final int pagesize,Boolean isReply,
			Date consultTime, Integer roomID, String roomNum);
	
	/**
	 * 获取记录总数
	 * @param isReply			是否已回复
	 * @param consultTime	咨询时间
	 * @param roomID	房间ID
	 * @param roomNum	房号
	 * @return	返回记录总数
	 */
	int getRecordNum(Boolean isReply,Date consultTime, 
			Integer roomID, String roomNum);
}
