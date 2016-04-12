package com.smtl.fweb.dao;

import java.util.List;

import com.smtl.fweb.damain.VipUser;

public interface VipUserDao {

	/**
	 * 根据用户获取用户实例
	 * @param userId		用户ID
	 * @return	VIP用户实例
	 */
	VipUser get(Integer userId);
	
	/**
	 * 根据用户名获取用户实例
	 * @param userName		用户名
	 * @return	VIP用户实例
	 */
	VipUser getFormName(String userName);
	
	/**
	 * 根据VIP卡号获取VIP用户实例
	 * @param vipCardNum	VIP卡号
	 * @return	VIP用户实例
	 */
	VipUser getFromVipCardNum(String vipCardNum);
	
	
	/**
	 * 保存持久化的实例
	 * @param user	vipUser持久化对象
	 * @return	持久化对象标识属性（ID）
	 */
	Integer save(VipUser user);
	
	/**
	 * 更新持久化实例
	 * @param user	vipUser持久化实例
	 */
	void update(VipUser user);
	
	/**
	 * 删除持久化实例
	 * @param user	vipUser持久化实例
	 */
	void delete(VipUser user);
	
	/**
	 * 根据持久化实例
	 * @param userId 用户持久化对象Id
	 */
	void delete(Integer userId);
	
	/**
	 * 获取所有用户实例
	 * @return	用户实例集合
	 */
	List<VipUser> findAll();
	
	/**
	 * 根据页码分页查询信息
	 * @param offset	起始索引
	 * @param pagesize	页大小
	 * @return	指定页信息集合
	 */
	List<VipUser> findByPage(final int offset,final int pagesize);
	
	/**
	 * 获取记录总数
	 * @return	返回记录总数
	 */
	int getRecordNum();
}
