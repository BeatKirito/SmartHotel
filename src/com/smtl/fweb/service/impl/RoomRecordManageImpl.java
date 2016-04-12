package com.smtl.fweb.service.impl;

import java.util.List;

import com.smtl.fweb.damain.RoomOrder;
import com.smtl.fweb.damain.RoomRecord;
import com.smtl.fweb.dao.RoomOrderDao;
import com.smtl.fweb.dao.RoomRecordDao;
import com.smtl.fweb.service.RoomRecordManage;

public class RoomRecordManageImpl implements RoomRecordManage{

	private RoomRecordDao roomRecordDao;

	
	public RoomRecordDao getRoomRecordDao() {
		return roomRecordDao;
	}



	public void setRoomRecordDao(RoomRecordDao roomRecordDao) {
		this.roomRecordDao = roomRecordDao;
	}



	//保存房间信息
	@Override
	public int saveRoomRecord(RoomRecord roomRecord) {
			
		return roomRecordDao.save(roomRecord);
	}


	//根据房间记录获取房间记录
	@Override
	public RoomRecord getRoomRecord(int RoomRecordID) {
		
		return roomRecordDao.get(RoomRecordID);
	}


	//根据房间号获取记录集合
	@Override
	public List<RoomRecord> getRoomRecordByRoomNum(String roomNumber) {
		
		return roomRecordDao.getFormCardNum(roomNumber);
	}
}
