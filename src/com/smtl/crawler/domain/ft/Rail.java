package com.smtl.crawler.domain.ft;

import java.util.Date;

public class Rail {

	private String railNo = "";
	private String dCity = "";
	private String dStation = "";
	private String aCity = "";
	private String aStataion = "";
	private Date dTime;
	private Date aTime;
	
	public String getRailNo() {
		return railNo;
	}
	public void setRailNo(String railNo) {
		this.railNo = railNo;
	}
	public String getdCity() {
		return dCity;
	}
	public void setdCity(String dCity) {
		this.dCity = dCity;
	}
	public String getdStation() {
		return dStation;
	}
	public void setdStation(String dStation) {
		this.dStation = dStation;
	}
	public String getaCity() {
		return aCity;
	}
	public void setaCity(String aCity) {
		this.aCity = aCity;
	}
	public String getaStataion() {
		return aStataion;
	}
	public void setaStataion(String aStataion) {
		this.aStataion = aStataion;
	}
	public Date getdTime() {
		return dTime;
	}
	public void setdTime(Date dTime) {
		this.dTime = dTime;
	}
	public Date getaTime() {
		return aTime;
	}
	public void setaTime(Date aTime) {
		this.aTime = aTime;
	}
	
}
