package com.smtl.fweb.damain;

import java.util.Date;

/**
 * 房间预订订单对象
 * @author 少波
 */
public class RoomOrder {

	private Integer orderID = 0;						//房间预订订单ID
	private Integer roomID = 0;						//房间ID
	private String cusName = "";					//顾客姓名
	private String phoneNumber = "";			//顾客联系电话号码
	private Date liveInTime = new Date();		//入住时间
	private Date leaveTime = new Date();		//离开时间
	private boolean isAvailable = false;			//是否有效
	
	
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getLiveInTime() {
		return liveInTime;
	}
	public void setLiveInTime(Date liveInTime) {
		this.liveInTime = liveInTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
