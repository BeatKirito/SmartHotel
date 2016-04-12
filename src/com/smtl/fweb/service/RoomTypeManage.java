package com.smtl.fweb.service;

import com.smtl.fweb.damain.RoomType;

public interface RoomTypeManage {
	
	//根据房间类型ID获取房间类型信息
	RoomType getRoomType(int roomTypeID);

}
