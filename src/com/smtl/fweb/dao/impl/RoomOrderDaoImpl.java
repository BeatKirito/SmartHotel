package com.smtl.fweb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.RoomOrder;
import com.smtl.fweb.damain.RoomOrderWN;
import com.smtl.fweb.dao.RoomOrderDao;

public class RoomOrderDaoImpl extends HibernateDaoSupport implements RoomOrderDao {

	@Override
	public RoomOrder get(Integer orderID) {
		return getHibernateTemplate().get(RoomOrder.class, orderID);
	}

	@Override
	public Integer save(RoomOrder roomOrder) {
		return (Integer)getHibernateTemplate().save(roomOrder);
	}

	@Override
	public void update(RoomOrder roomOrder) {
		getHibernateTemplate().update(roomOrder);
	}

	@Override
	public void delete(RoomOrder roomOrder) {
		getHibernateTemplate().delete(roomOrder);
	}

	@Override
	public void delete(Integer orderID) {
		getHibernateTemplate().delete(get(orderID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomOrderWN> findAll() {
		return (List<RoomOrderWN>)getHibernateTemplate().find("from RoomOrderWN");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RoomOrderWN> findByPage(final int offset, final int pagesize,
			Boolean isAvailable, String cusName, Integer roomID, String roomNum) {
		
		final String hql = String.format(
				"from RoomOrderWN as t where 1 = 1 %s %s %s %s", 
				isAvailable == null ? "" : String.format("and t.isAvailable = %d", isAvailable ? 1 : 0),
				cusName == null ? "" : String.format("and t.cusName = '%s'", cusName),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		return (List<RoomOrderWN>)getHibernateTemplate().executeFind(new HibernateCallback() {

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
				"select count(*) from RoomOrderWN as t where 1 = 1 %s %s %s %s", 
				isAvailable == null ? "" : String.format("and t.isAvailable = %d", isAvailable ? 1 : 0),
				cusName == null ? "" : String.format("and t.cusName = '%s'", cusName),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		Number num = (Number)getHibernateTemplate().find(hql).get(0);
		return num.intValue();
	}

}
