package com.smtl.fweb.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.SUserType;
import com.smtl.fweb.dao.SUserTypeDao;

public class SUserTypeDaoImpl extends HibernateDaoSupport  implements SUserTypeDao {

	@Override
	public SUserType get(Integer typeID) {
		
		return getHibernateTemplate().get(SUserType.class, typeID);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public SUserType getFormName(String typeName) {
		
		List result = getHibernateTemplate().
				find("from SUserType as t where t.typeName = ?" , typeName);
		
		if(result.size() <= 0) return null;
		
		return (SUserType)result.get(0);
	}

	@Override
	public Integer save(SUserType userType) {
		return (Integer)getHibernateTemplate().save(userType);
	}

	@Override
	public void update(SUserType userType) {
		getHibernateTemplate().update(userType);
	}

	@Override
	public void delete(SUserType userType) {
		getHibernateTemplate().delete(userType);
	}

	@Override
	public void delete(Integer typeID) {
		getHibernateTemplate().delete(get(typeID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SUserType> findAll() {
		return (List<SUserType>)getHibernateTemplate().find("from SUserType");
	}

}
