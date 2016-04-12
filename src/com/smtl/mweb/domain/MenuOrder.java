package com.smtl.mweb.domain;

/**
 * 酒店餐饮订单对象
 * @author 少波
 */
public class MenuOrder {

	private Integer orderID = 0;				//预订订单ID
	private Integer roomID = 0;				//房间ID
	private Integer mealTimesID = 0;		//餐次ID
	private Integer orderListID = 0;		//订单列表ID
	private double totalPrice = 0;			//总费用
	
	
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
	public Integer getMealTimesID() {
		return mealTimesID;
	}
	public void setMealTimesID(Integer mealTimesID) {
		this.mealTimesID = mealTimesID;
	}
	public Integer getOrderListID() {
		return orderListID;
	}
	public void setOrderListID(Integer orderListID) {
		this.orderListID = orderListID;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
