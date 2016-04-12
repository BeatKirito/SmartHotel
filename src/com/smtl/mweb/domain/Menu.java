package com.smtl.mweb.domain;

/**
 * 酒店菜谱对象
 * @author 少波
 */
public class Menu {

	private Integer menuID = 0;			//菜谱菜式ID
	private String foodName = "";		//食物名称
	private String pictureAddr = "";	//食物图片地址
	private double price = 0;				//食物价格
	
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getPictureAddr() {
		return pictureAddr;
	}
	public void setPictureAddr(String pictureAddr) {
		this.pictureAddr = pictureAddr;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
