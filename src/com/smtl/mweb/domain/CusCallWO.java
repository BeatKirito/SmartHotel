package com.smtl.mweb.domain;

import java.util.Date;

/**
 * 顾客呼叫信息对象（包含其他关联数据）
 * @author 少波
 */
public class CusCallWO {

	private Integer callID = 0;						//呼叫信息ID
	private Integer roomID = 0;					//房间ID
	private Integer suserTypeID = 0;			//呼叫的用户职务（类型）ID
	private Integer acceptedSUserID = 0;	//被受理的
	private Integer state = 0;						//呼叫状态（0未受理 1待完成 2已完成 -1已取消）
	private String statement = "";				//完成情况说明
	private Date callTime = new Date();		//呼叫时间
	private String title = "";						//标题
	
	private String roomNum = "";				//房号
	private String userTypeName = "";		//用户类别名称
	private String userName = "";				//用户名
	
	public Integer getCallID() {
		return callID;
	}
	public void setCallID(Integer callID) {
		this.callID = callID;
	}
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public Integer getSuserTypeID() {
		return suserTypeID;
	}
	public void setSuserTypeID(Integer suserTypeID) {
		this.suserTypeID = suserTypeID;
	}
	public Integer getAcceptedSUserID() {
		return acceptedSUserID;
	}
	public void setAcceptedSUserID(Integer acceptedSUserID) {
		this.acceptedSUserID = acceptedSUserID;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
