package com.smtl.fweb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.Room;
import com.smtl.fweb.damain.RoomWT;
import com.smtl.fweb.dao.RoomDao;

public class RoomDaoImpl extends HibernateDaoSupport implements RoomDao {

	@Override
	public Room get(Integer roomID) {
		return getHibernateTemplate().get(Room.class, roomID);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RoomWT getFormRoomNum(String roomNum) {
		
		List result = getHibernateTemplate().
				find("from RoomWT as t where t.roomNum = ?" , roomNum);
		
		if(result.size() <= 0) return null;
		
		return (RoomWT)result.get(0);
	}
	

	@Override
	public Integer save(Room room) {
		return (Integer)getHibernateTemplate().save(room);
	}

	@Override
	public void update(Room room) {
		getHibernateTemplate().update(room);
	}

	@Override
	public void delete(Room room) {
		getHibernateTemplate().delete(room);
	}

	@Override
	public void delete(Integer roomID) {
		getHibernateTemplate().delete(get(roomID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomWT> findAll() {
		return (List<RoomWT>)getHibernateTemplate().find("from RoomWT");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RoomWT> findByPage(final int offset, final int pagesize, Integer floor,
			Boolean state, Integer typeID, String typeName) {
		
		final String hql = String.format(
				"from RoomWT as t where 1= 1 %s %s %s %s", 
				floor == null ? "" : String.format("and t.floor = %d", floor),
				state == null ? "" : String.format("and t.state = %d", state ? 1 : 0),
				typeID == null ? "" : String.format("and t.roomTypeID = %d", typeID), 
				typeName == null ? "" : String.format("and t.roomTypeName = '%s'", typeName));
		
		return (List<RoomWT>)getHibernateTemplate().executeFind(new HibernateCallback() {

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
	public int getRecordNum(Integer floor, Boolean state, Integer typeID,
			String typeName) {
		
		String hql = String.format(
				"select count(*) from RoomWT as t where 1 = 1 %s %s %s %s", 
				floor == null ? "" : String.format("and t.floor = %d", floor),
				state == null ? "" : String.format("and t.state = %d", state ? 1 : 0),
				typeID == null ? "" : String.format("and t.roomTypeID = %d", typeID), 
				typeName == null ? "" : String.format("and t.roomTypeName = '%s'", typeName));
		
		Number num = (Number)getHibernateTemplate().find(hql).get(0);
		return num.intValue();
	}

}
