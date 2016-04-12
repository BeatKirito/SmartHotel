package com.smtl.mweb.domain;

/**
 * 酒店餐饮订单对象
 * @author 少波
 */
public class MenuOrderList {

	private Integer listID = 0;				//列表项ID
	private Integer menuID = 0;			//菜式ID
	private Integer orderNum = 0;		//点餐数量
	
	public Integer getListID() {
		return listID;
	}
	public void setListID(Integer listID) {
		this.listID = listID;
	}
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}
