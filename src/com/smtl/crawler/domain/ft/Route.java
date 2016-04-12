package com.smtl.crawler.domain.ft;

import java.util.Date;
import java.util.List;

/**
 * 机票路线
 * @author beat
 *
 */
public class Route {

	private String dCity = "";			//出发城市					
	private String aCity = "";			//到达城市
	private Date dDate ;					//出发日期
	private int pNum;					//人数
	private PType pType;				//乘客类型
	private CType cType;				//舱位等级
	
	private List<Flight> flights = null;		//所查询的来的航班集合

	public String getdCity() {
		return dCity;
	}

	public void setdCity(String dCity) {
		this.dCity = dCity;
	}

	public String getaCity() {
		return aCity;
	}

	public void setaCity(String aCity) {
		this.aCity = aCity;
	}

	public Date getdDate() {
		return dDate;
	}

	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public PType getpType() {
		return pType;
	}

	public void setpType(PType pType) {
		this.pType = pType;
	}

	public CType getcType() {
		return cType;
	}

	public void setcType(CType cType) {
		this.cType = cType;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
}
