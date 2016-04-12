package com.smtl.crawler.domain.ft;

/**
 * 机票详细信息
 * @author beat
 *
 */
public class Ticket {

	private double price;					//价格	
	private boolean isSaleOut;		//是否售空
	private int remain;					//余票量
	private String classType = "";	//舱位等级
	
	private String seatType = "";    //座位等级
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isSaleOut() {
		return isSaleOut;
	}
	public void setSaleOut(boolean isSaleOut) {
		this.isSaleOut = isSaleOut;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	
	
	
}
