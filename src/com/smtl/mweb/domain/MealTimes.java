package com.smtl.mweb.domain;

import java.util.Date;

/**
 * 酒店餐次对象
 * @author 少波
 */
public class MealTimes {

	private Integer timesID = 0;					//餐次ID
	private String timesName = "";				//餐次名称
	private Date startTime = new Date();	//餐次开始时间
	private Date endTime = new Date();		//餐次结束时间
	
	public Integer getTimesID() {
		return timesID;
	}
	public void setTimesID(Integer timesID) {
		this.timesID = timesID;
	}
	public String getTimesName() {
		return timesName;
	}
	public void setTimesName(String timesName) {
		this.timesName = timesName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
