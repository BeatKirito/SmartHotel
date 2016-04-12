package com.smtl.fweb.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.CusConsult;
import com.smtl.fweb.damain.CusConsultWN;
import com.smtl.fweb.dao.CusComsultDao;

public class CusComsultDaoImpl extends HibernateDaoSupport implements CusComsultDao {

	@Override
	public CusConsult get(Integer consultID) {
		return getHibernateTemplate().get(CusConsult.class, consultID);
	}

	@Override
	public Integer save(CusConsult cusConsult) {
		return (Integer)getHibernateTemplate().save(cusConsult);
	}

	@Override
	public void update(CusConsult cusConsult) {
		getHibernateTemplate().update(cusConsult);
	}

	@Override
	public void delete(CusConsult cusConsult) {
		getHibernateTemplate().delete(cusConsult);
	}

	@Override
	public void delete(Integer consultID) {
		getHibernateTemplate().delete(get(consultID));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CusConsultWN> findAll() {
		return (List<CusConsultWN>)getHibernateTemplate().find("from CusConsultWN");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CusConsultWN> findByPage(final int offset,final int pagesize,
			Boolean isReply, Date consultTime, Integer roomID, String roomNum) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		final String hql = String.format(
				"from CusConsultWN as t where 1 = 1 %s %s %s %s", 
				isReply == null ? "" : String.format("and t.isReply = %d", isReply ? 1 : 0),
				consultTime == null ? "" : String.format("and to_char(t.consultTime,'yyyy-MM-dd') = '%s'", sdf.format(consultTime)),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		return (List<CusConsultWN>)getHibernateTemplate().executeFind(new HibernateCallback() {

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
	public int getRecordNum(Boolean isReply, Date consultTime, Integer roomID,
			String roomNum) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hql = String.format(
				"select count(*) from CusConsultWN as t where 1 = 1 %s %s %s %s", 
				isReply == null ? "" : String.format("and t.isReply = %d", isReply ? 1 : 0),
				consultTime == null ? "" : String.format("and to_char(t.consultTime,'yyyy-MM-dd') = '%s'", sdf.format(consultTime)),
				roomID == null ? "" : String.format("and t.roomID = %d", roomID), 
				roomNum == null ? "" : String.format("and t.roomNum = '%s'", roomNum));
		
		Number num = (Number)getHibernateTemplate().find(hql).get(0);
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CusConsultWN> findAllNoReply() {
		
		return (List<CusConsultWN>)getHibernateTemplate().find("from CusConsult where isReply=0");

	}

}
