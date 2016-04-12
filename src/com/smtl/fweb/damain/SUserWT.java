package com.smtl.fweb.damain;

/**
 * 酒店餐饮订单对象
 * @author 少波
 */
public class SUserWT {

	private Integer userID = 0;				//系统用户ID
	private Integer userTypeID = 0;		//用户类型ID
	private String userTypeName = "";	//系统用户类别名称
	private String account = "";				//系统登陆账号
	private String userName = "";			//用户名
	private String password = "";			//密码
	private boolean isManager = false;	//是否为管理员
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getUserTypeID() {
		return userTypeID;
	}
	public void setUserTypeID(Integer userTypeID) {
		this.userTypeID = userTypeID;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	
}
