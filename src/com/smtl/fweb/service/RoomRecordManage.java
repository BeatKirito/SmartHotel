package com.smtl.fweb.service;

import java.util.List;

import com.smtl.fweb.damain.RoomRecord;

public interface RoomRecordManage {

	/**
	 * 保存开房记录
	 * @param roomOrder
	 * @return
	 */
	int saveRoomRecord(RoomRecord roomRecord);
	
	/**
	 * 根据房间记录ID获取房间记录
	 * @param RoomRecordID
	 * @return
	 */
	RoomRecord getRoomRecord(int RoomRecordID);
	
	/**
	 * 根据身份证号码获取顾客的入住信息
	 * @param cardNumber
	 * @return
	 */
	List<RoomRecord> getRoomRecordByRoomNum(String roomNumber);
}
