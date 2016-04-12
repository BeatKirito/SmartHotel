package com.smtl.fweb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.RoomRecord;
import com.smtl.fweb.damain.RoomRecordWN;
import com.smtl.fweb.damain.RoomWT;
import com.smtl.fweb.dao.RoomRecordDao;

public class RoomRecordDaoImpl extends HibernateDaoSupport implements RoomRecordDao {

	@Override
	public RoomRecord get(Integer recordID) {
		return getHibernateTemplate().get(RoomRecord.class, recordID);
	}
	
	@Override
	public List<RoomRecord> getFormCardNum(String cardNum) {
		
		@SuppressWarnings("unchecked")
		List<RoomRecord> result = getHibernateTemplate().
				find("from RoomRecord as t where t.roomID = ? order by t.liveInTime" , Integer.parseInt(cardNum));
		
		if(result.size() <= 0) return null;
		
		return result;
	}

	@Override
	public Integer save(RoomRecord roomRecord) {
		return (Integer)getHibernateTemplate().save(roomRecord);
	}

	@Override
	public void update(RoomRecord roomRecord) {
		getHibernateTemplate().update(roomRecord);
	}

	@Override
	public void delete(RoomRecord roomRecord) {
		getHibernateTemplate().delete(roomRecord);
	}

	@Override
	public void delete(Integer recordID) {
		getHibernateTemplate().delete(get(recordID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomRecordWN> findAll() {
		return (List<RoomRecordWN>)getHibernateTemplate().find("from RoomRecordWN");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RoomRecordWN> findByPage(final int offset, final int pagesize,
			Boolean isAvailable, String cusName, Integer roomID, String roomNum) {
		
		final String hql = String.format(
				"from RoomRecordWN as t where 1 = 1 %s %s %s %s", 
				isAvailable == null ? "" : String.format("and t.isAvailable = %d", isAvailable ? 1 : 0),
				cusName == null ? "" : String.format("and t.cusName = '%s'", cusName),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		return (List<RoomRecordWN>)getHibernateTemplate().executeFind(new HibernateCallback() {

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
	public int getRecordNum(Boolean isAvailable, String cusName,
			Integer roomID, String roomNum) {
		
		String hql = String.format(
				"select count(*) from RoomRecordWN as t where 1 = 1 %s %s %s %s", 
				isAvailable == null ? "" : String.format("and t.isAvailable = %d", isAvailable ? 1 : 0),
				cusName == null ? "" : String.format("and t.cusName = '%s'", cusName),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		Number num = (Number)getHibernateTemplate().find(hql).get(0);
		return num.intValue();
	}

}
