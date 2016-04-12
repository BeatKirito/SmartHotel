package com.smtl.fweb.damain;

/**
 * 房间类型对象
 * @author 少波
 */
public class RoomType {

	private Integer typeID = 0;		//房间类型ID
	private String typeName = "";	//房间类型名称
	private double price = 0;			//房价
	
	
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
