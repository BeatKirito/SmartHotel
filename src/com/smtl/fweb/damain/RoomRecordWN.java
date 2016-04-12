package com.smtl.fweb.damain;

import java.util.Date;

public class RoomRecordWN {

	private Integer recordID = 0;					//预订订单ID
	private Integer roomID = 0;						//房间ID
	private String cusName = "";					//顾客姓名
	private String cardNumber = "";				//证件号码
	private String phoneNumber = "";			//顾客联系电话
	private Date liveInTime = new Date();		//入住时间
	private Date leaveTime = new Date();		//离开时间
	private double baseCost = 0;					//基本费用
	private double deposit = 0;						//押金
	private double initCost = 0;						//初始费用
	private double ortherCost = 0;					//其他费用
	private double returnDeposit = 0;			//返还的押金
	private boolean isAvailable = false;			//是否有效
	
	private String roomNum = "";					//房号

	public Integer getRecordID() {
		return recordID;
	}

	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getInitCost() {
		return initCost;
	}

	public void setInitCost(double initCost) {
		this.initCost = initCost;
	}

	public double getOrtherCost() {
		return ortherCost;
	}

	public void setOrtherCost(double ortherCost) {
		this.ortherCost = ortherCost;
	}

	public double getReturnDeposit() {
		return returnDeposit;
	}

	public void setReturnDeposit(double returnDeposit) {
		this.returnDeposit = returnDeposit;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
}
