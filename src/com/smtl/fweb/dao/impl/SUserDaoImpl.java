package com.smtl.fweb.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.dao.SUserDao;

public class SUserDaoImpl extends HibernateDaoSupport  implements SUserDao {

	@Override
	public SUser get(Integer userId) {
		
		return getHibernateTemplate().get(SUser.class, userId);
	}

	@Override
	public SUserWT getFormName(String userName) {
		
		@SuppressWarnings("rawtypes")
		List result = getHibernateTemplate().
				find("from SUserWT as t where t.userName = ?" , userName);
		
		if(result.size() <= 0) return null;
		
		return (SUserWT)result.get(0);
	}
	
	@Override
	public SUserWT getFormAccount(String Account) {
		
		@SuppressWarnings("rawtypes")
		List result = getHibernateTemplate().
				find("from SUserWT as t where t.account = ?" , Account);
		
		if(result.size() <= 0) return null;
		
		return (SUserWT)result.get(0);
	}

	@Override
	public Integer save(SUser user) {
		
		return (Integer)getHibernateTemplate().save(user);
	}

	@Override
	public void update(SUser user) {
		
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(SUser user) {
		
		getHibernateTemplate().delete(user);
	}

	@Override
	public void delete(Integer userId) {
		
		getHibernateTemplate().delete(get(userId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SUserWT> findAll() {
		
		return (List<SUserWT>)getHibernateTemplate().find("from SUserWT");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SUserWT> findWithParams(Integer typeID, String typeName) {
		
		String hql = String.format("from SUserWT as t where 1 = 1  %s  %s ", 
				typeID == null ?  "" : String.format("and t.userTypeID = %d", typeID), 
				typeName == null ? "" : String.format("and t.userTypeName = '%s'", typeName));
		
		return (List<SUserWT>)getHibernateTemplate().find(hql);
	}

}
