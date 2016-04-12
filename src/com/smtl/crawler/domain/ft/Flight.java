package com.smtl.crawler.domain.ft;

import java.util.Date;
import java.util.List;

/**
 * 机票航班信息
 * @author beat
 *
 */
public class Flight {

	private String airLine = "";			//航空公司
	private String alCode = "";			//航空公司代码
	private String flightNo = "";			//航班代码
	private String ft = "";					//计划机型，例如 333
	private char ftSize;						//机型大小，例如 大 中 小
	private String dAirPort;					//出发机场
	private String aAirPort;					//到达机场
	private Date dTime;						//出发具体时间
	private Date aTime;						//到达具体时间
	private double pr;							//准点到达率（百分比）
	private boolean isCate;				//是否有餐食
	private double tax;							//机建费
	private double yp;							//燃油费
	private double lowPrice;					//机票起价  
	
	private boolean isHasRail = false;		//是否含有铁路（是否是空铁组合）
	private Rail rail = null;							//铁路信息
	
	private List<Ticket> tickets;			//航班所带的机票

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getAlCode() {
		return alCode;
	}

	public void setAlCode(String alCode) {
		this.alCode = alCode;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public char getFtSize() {
		return ftSize;
	}

	public void setFtSize(char ftSize) {
		this.ftSize = ftSize;
	}

	public String getdAirPort() {
		return dAirPort;
	}

	public void setdAirPort(String dAirPort) {
		this.dAirPort = dAirPort;
	}

	public String getaAirPort() {
		return aAirPort;
	}

	public void setaAirPort(String aAirPort) {
		this.aAirPort = aAirPort;
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

	public double getPr() {
		return pr;
	}

	public void setPr(double pr) {
		this.pr = pr;
	}

	public boolean isCate() {
		return isCate;
	}

	public void setCate(boolean isCate) {
		this.isCate = isCate;
	}
	

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getYp() {
		return yp;
	}

	public void setYp(double yp) {
		this.yp = yp;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean isHasRail() {
		return isHasRail;
	}

	public void setHasRail(boolean isHasRail) {
		this.isHasRail = isHasRail;
	}

	public Rail getRail() {
		return rail;
	}

	public void setRail(Rail rail) {
		this.rail = rail;
	}
	
	
	
}
