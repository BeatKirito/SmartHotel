package com.smtl.fweb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smtl.fweb.damain.VipUser;
import com.smtl.fweb.dao.VipUserDao;

public class VipUserDaoImpl extends HibernateDaoSupport implements VipUserDao {

	@Override
	public VipUser get(Integer userId) {
		return getHibernateTemplate().get(VipUser.class, userId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public VipUser getFormName(String userName) {
		
		List result = getHibernateTemplate().
				find("from VipUser as t where t.userName = ?" , userName);
		
		if(result.size() <= 0) return null;
		
		return (VipUser)result.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public VipUser getFromVipCardNum(String vipCardNum) {
		
		List result = getHibernateTemplate().
				find("from VipUser where VipUser.cardNumber = ?" , vipCardNum);
		
		if(result.size() <= 0) return null;
		
		return (VipUser)result.get(0);
	}

	@Override
	public Integer save(VipUser user) {
		return (Integer)getHibernateTemplate().save(user);
	}

	@Override
	public void update(VipUser user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(VipUser user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public void delete(Integer userId) {
		getHibernateTemplate().delete(get(userId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VipUser> findAll() {
		return (List<VipUser>)getHibernateTemplate().find("from VipUser");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<VipUser> findByPage(final int offset, final int pagesize) {
		
		return (List<VipUser>)getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session paramSession)
					throws HibernateException, SQLException {
				return paramSession.createQuery("from VipUser")
						.setFirstResult(offset)
						.setMaxResults(pagesize)
						.list();
			}
		});
	}

	@Override
	public int getRecordNum() {
		Number num = (Number)getHibernateTemplate()
				.find("select count(*) from VipUser").get(0);
		return num.intValue();
	}

}
