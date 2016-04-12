package com.smtl.fweb.service;

import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.damain.SUserWT;

public interface SUserManage {

	/**
	 * 创建一个新的系统用户
	 * @param sUser		新建的用户对象
	 * @return				是否成功新建用户
	 */
	boolean CreateSUser(SUser sUser);
	
	/**
	 * 用户登录时进行身份验证，通过身份验证后，
	 * 绑定用户信息到用户对象
	 * @param sUser	待验证的用户对象
	 * @return			是否成功验证
	 */
	boolean userValidate(SUserWT sUser);
}
