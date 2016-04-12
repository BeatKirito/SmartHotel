package com.smtl.mweb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.mweb.dao.CusCallDao;
import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.domain.CusCallWO;

public class CusCallDaoImpl extends HibernateDaoSupport implements CusCallDao {

	@Override
	public CusCall get(Integer callID) {
		return getHibernateTemplate().get(CusCall.class, callID);
	}

	@Override
	public Integer save(CusCall cusCall) {
		return (Integer)getHibernateTemplate().save(cusCall);
	}

	@Override
	public void update(CusCall cusCall) {
		getHibernateTemplate().update(cusCall);
	}

	@Override
	public void delete(CusCall cusCall) {
		getHibernateTemplate().delete(cusCall);
	}

	@Override
	public void delete(Integer callID) {
		getHibernateTemplate().delete(get(callID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CusCallWO> findAll() {
		return (List<CusCallWO>)getHibernateTemplate().find("from CusCallWO");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CusCallWO> findByPage(final int offset,final int pagesize,
			Integer suserTypeID, Integer roomID, String roomNum,
			 Integer state, Integer sUserID) {
		
		final String hql = String.format(
				"from CusCallWO as t where 1 = 1 %s %s %s %s %s", 
				suserTypeID == null ? "" : String.format("and t.suserTypeID = %d", suserTypeID),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID),
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum), 
				sUserID == null ? "" : String.format("and t.acceptedSUserID = %d", sUserID),
				state == null ? "" : String.format("and t.state = %d", state));
		return (List<CusCallWO>)getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session paramSession)
					throws HibernateException, SQLException {
				return paramSession.createQuery(hql)
						.setFirstResult(offset)
						.setMaxResults(pagesize)
						.list();
			}
		});
	}

	@Override
	public int getRecordNum(Integer suserTypeID, Integer roomID,
			String roomNum,  Integer state, Integer sUserID) {
		
		String hql = String.format(
				"select count(*) from CusCallWO as t where 1 = 1 %s %s %s %s %s", 
				suserTypeID == null ? "" : String.format("and t.suserTypeID = %d", suserTypeID),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID),
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum), 
				sUserID == null ? "" : String.format("and t.acceptedSUserID = %d", sUserID),
				state == null ? "" : String.format("and t.state = %d", state));
		
		Number num = (Number)getHibernateTemplate().find(hql).get(0);
		return num.intValue();
	}

	@Override
	public CusCallWO getWO(Integer callID) {
		return getHibernateTemplate().get(CusCallWO.class, callID);
	}

}
