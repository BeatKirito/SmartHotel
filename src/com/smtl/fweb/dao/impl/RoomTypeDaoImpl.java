package com.smtl.fweb.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.RoomType;
import com.smtl.fweb.dao.RoomTypeDao;

public class RoomTypeDaoImpl extends HibernateDaoSupport implements RoomTypeDao {

	@Override
	public RoomType get(Integer typeID) {
		return getHibernateTemplate().get(RoomType.class, typeID);
	}

	@Override
	public RoomType getFormName(String typeName) {
		
		@SuppressWarnings("rawtypes")
		List result = getHibernateTemplate().
				find("from RoomType as t where t.typeName = ?" , typeName);
		
		if(result.size() <= 0) return null;
		
		return (RoomType)result.get(0);
	}

	@Override
	public Integer save(RoomType roomType) {
		return (Integer)getHibernateTemplate().save(roomType);
	}

	@Override
	public void update(RoomType roomType) {
		getHibernateTemplate().update(roomType);
	}

	@Override
	public void delete(RoomType roomType) {
		getHibernateTemplate().delete(roomType);
	}

	@Override
	public void delete(Integer typeID) {
		getHibernateTemplate().delete(get(typeID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> findAll() {
		return (List<RoomType>)getHibernateTemplate().find("from RoomType");
	}

}
