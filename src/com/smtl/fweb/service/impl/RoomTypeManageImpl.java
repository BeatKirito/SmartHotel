package com.smtl.fweb.service.impl;

import com.smtl.fweb.damain.RoomType;
import com.smtl.fweb.dao.RoomTypeDao;
import com.smtl.fweb.service.RoomTypeManage;

public class RoomTypeManageImpl implements RoomTypeManage{

	RoomTypeDao roomTypeDao;
	
	public RoomTypeDao getRoomTypeDao() {
		return roomTypeDao;
	}

	public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
	}



	@Override
	public RoomType getRoomType(int roomTypeID) {
		
		//根据房间类型ID获取房间类型信息
		return roomTypeDao.get(roomTypeID);
	}

	
}
