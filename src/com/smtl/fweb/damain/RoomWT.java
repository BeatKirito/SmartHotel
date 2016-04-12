package com.smtl.fweb.damain;

public class RoomWT {

	private Integer roomID = 0;			//房间ID
	private Integer roomTypeID = 0;	//房间类型ID
	private String roomNum = "";		//房号
	private Integer floor = 0;				//所属楼层
	private boolean state = false;		//当前状态（是否已入住）
	private String roomTypeName = "";		//房间类型名称
	private double price = 0;				//房价
	private String password = "";		//房间登陆密码
	
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public Integer getRoomTypeID() {
		return roomTypeID;
	}
	public void setRoomTypeID(Integer roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String typeName) {
		this.roomTypeName = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
