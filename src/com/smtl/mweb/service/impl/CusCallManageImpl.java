package com.smtl.mweb.service.impl;

import java.util.List;

import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.dao.SUserDao;
import com.smtl.mweb.service.CusCallManage;
import com.smtl.mweb.dao.CusCallDao;
import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.domain.CusCallWO;

public class CusCallManageImpl implements CusCallManage {
	
	private CusCallDao cusCallDao;
	private SUserDao sUserDao;

	public CusCallDao getCusCallDao() {
		return cusCallDao;
	}
	public void setCusCallDao(CusCallDao cusCallDao) {
		this.cusCallDao = cusCallDao;
	}
	public SUserDao getsUserDao() {
		return sUserDao;
	}
	public void setsUserDao(SUserDao sUserDao) {
		this.sUserDao = sUserDao;
	}
	@Override
	public boolean createACall(CusCall cusCall) {
		
		//由于工作人员职务暂时写死，这里的Title也暂时写死
		Integer sutID = cusCall.getSuserTypeID();
		switch (sutID) {
		case 2:
			cusCall.setTitle("客房清洁"); 
			break;
		case 5:
			cusCall.setTitle("客房设备维修"); 
			break;
		default:
			cusCall.setTitle("客户呼叫"); 
			break;
		}
		
		if(cusCallDao.save(cusCall) < 0) return false;
		return true;
	}
	@Override
	public List<CusCallWO> loadListBySUTypeID(Integer SUTypeID) {
		
		int recordNum = cusCallDao.getRecordNum(SUTypeID, null, null, 0 , null);
		return cusCallDao.findByPage(0, recordNum, SUTypeID, null, null, 0, null);
	}
	
	@Override
	public CusCallWO loadByID(Integer callID) {
		return cusCallDao.getWO(callID);
	}
	@Override
	public boolean acceptCall(CusCallWO cusCall) {
		
		boolean isSuccess = true;
		CusCall preCall = cusCallDao.get(cusCall.getCallID());
		
		if(preCall.getState() != 0) isSuccess = false;	//若非未受理状态，则返回false
		else {	
			//更新状态和接受人ID
			preCall.setAcceptedSUserID(cusCall.getAcceptedSUserID());
			preCall.setState(1);
			cusCallDao.update(preCall);
		}
		
		//更新接收呼叫后的状态
		CusCallWO tCusCall = cusCallDao.getWO(cusCall.getCallID());
		cusCall.setCallTime(tCusCall.getCallTime());
		cusCall.setAcceptedSUserID(tCusCall.getAcceptedSUserID());
		cusCall.setRoomID(tCusCall.getRoomID());
		cusCall.setRoomNum(tCusCall.getRoomNum());
		cusCall.setSuserTypeID(tCusCall.getSuserTypeID());
		cusCall.setTitle(tCusCall.getTitle());
		cusCall.setUserName(tCusCall.getUserName());
		cusCall.setUserTypeName(tCusCall.getUserTypeName());
		cusCall.setState(tCusCall.getState());
		
		return isSuccess;
	}
	@Override
	public boolean completeCall(CusCallWO cusCall) {
		
		CusCall preCall = cusCallDao.get(cusCall.getCallID());
		
		if(preCall.getState() != 1) return false;	//若非未受理状态，则返回false
		
		//若接收呼叫的人和完成呼叫的人不一致，则返回false
		if(preCall.getAcceptedSUserID() != cusCall.getAcceptedSUserID()) return false;
		preCall.setStatement(cusCall.getStatement()); 
		preCall.setState(2);
		cusCallDao.update(preCall);
		
		//更新接收呼叫后的状态
		CusCallWO tCusCall = cusCallDao.getWO(cusCall.getCallID());
		cusCall.setCallTime(tCusCall.getCallTime());
		cusCall.setRoomID(tCusCall.getRoomID());
		cusCall.setRoomNum(tCusCall.getRoomNum());
		cusCall.setSuserTypeID(tCusCall.getSuserTypeID());
		cusCall.setTitle(tCusCall.getTitle());
		cusCall.setUserName(tCusCall.getUserName());
		cusCall.setUserTypeName(tCusCall.getUserTypeName());
		cusCall.setState(tCusCall.getState()); 
		
		return true;
	}
	@Override
	public List<CusCallWO> loadToBeComCalls(Integer SUserID) {
		
		int recordNum = cusCallDao.getRecordNum(null, null, null, 1 , SUserID);
		return cusCallDao.findByPage(0, recordNum, null, null, null, 1, SUserID);
	}

}
