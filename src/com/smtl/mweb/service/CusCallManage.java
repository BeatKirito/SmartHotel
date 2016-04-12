package com.smtl.mweb.service;

import java.util.List;

import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.domain.CusCallWO;

public interface CusCallManage {

	/**
	 * 创建一条客户呼叫记录
	 * @param cusCall		客户呼叫对象
	 * @return					是否创建成功
	 */
	boolean createACall(CusCall cusCall);
	
	/**
	 * 根据工作人员职务类型获取相应客户呼叫列表
	 * @param SUTypeID	工作人员职务类型ID
	 * @return	相应职务的顾客呼叫列表
	 */
	List<CusCallWO> loadListBySUTypeID(Integer SUTypeID);
	
	/**
	 * 根据呼叫ID获取客户呼叫详细信息
	 * @param callID	呼叫ID	
	 * @return	返回客户呼叫详细信息
	 */
	CusCallWO loadByID(Integer callID);
	
	/**
	 * 接收呼叫
	 * @param callID 呼叫ID
	 * @return	是否成功接收
	 */
	boolean acceptCall(CusCallWO cusCall);
	
	/**
	 * 完成呼叫
	 * @param cusCall	呼叫ID
	 * @return	是否完成呼叫
	 */
	boolean completeCall(CusCallWO cusCall);
	
	/**
	 * 根据用户ID，获取用户未完成的呼叫列表
	 * @param SUserID	用户ID
	 * @return	呼叫列表
	 */
	List<CusCallWO> loadToBeComCalls(Integer SUserID);
}
