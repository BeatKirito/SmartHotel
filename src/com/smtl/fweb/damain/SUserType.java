package com.smtl.fweb.damain;

/**
 * 系统用户类别对象
 * @author 少波
 */
public class SUserType {

	private Integer typeID = 0;		//系统用户类别ID
	private String typeName = "";	//类别名称
	private String description = "";	//类别描述
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
