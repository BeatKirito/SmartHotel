package com.smtl.fweb.damain;

import java.util.Date;

/**
 * VIP用户对象
 * @author 少波
 */
public class VipUser {

	private Integer userID = 0;						//用户ID
	private String account = "";						//登陆账号
	private String userName = "";					//用户名
	private String password = "";					//密码
	private String cardNumber = "";				//会员卡号
	private String phoneNumber = "";			//联系电话号码
	private double balance = 0;						//账户余额
	private Date registerDate = new Date();	//账号申请日期
	private Date lastDate = new Date();			//最后入住日期
	
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
}
