package com.smtl.fweb.service.impl;

import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.dao.SUserDao;
import com.smtl.fweb.service.SUserManage;

public class SUserManageImpl implements SUserManage {
	
	private SUserDao sUserDao;
	
	public SUserDao getsUserDao() {
		return sUserDao;
	}
	public void setsUserDao(SUserDao sUserDao) {
		this.sUserDao = sUserDao;
	}

	@Override
	public boolean CreateSUser(SUser sUser) {
		
		if(sUserDao.save(sUser) > 0 ) return true;	
		return false;
	}
	@Override
	public boolean userValidate(SUserWT sUser) {
		
		String account = sUser.getAccount();
		
		//第一步：获取指定用户名用户的正确数据
		SUserWT tSUser = sUserDao.getFormAccount(account);
		
		//第二步：验证输入的用户数据（用户是否存在以及密码是否正确）
		if(tSUser == null || !tSUser.getPassword().equals(sUser.getPassword())) 
			return false;
		
		//第三步：绑定通过验证的用户信息到用户对象,并将密码置空
		sUser.setUserID(tSUser.getUserID());
		sUser.setAccount(tSUser.getAccount());
		sUser.setUserName(tSUser.getUserName());
		sUser.setUserTypeID(tSUser.getUserTypeID());
		sUser.setUserTypeName(tSUser.getUserTypeName());
		sUser.setIsManager(tSUser.getIsManager());
		sUser.setPassword(""); 
		
		return true;
	}

	
}
