package com.smtl.fweb.damain;

import java.util.Date;

/**
 * 用户咨询信息对象
 * @author 少波
 */
public class CusConsult {

	private Integer consultID = 0;					//咨询信息ID
	private Integer roomID = 0;						//房间ID
	private String content = "";					//咨询内容
	private Date consultTime = new Date();			//咨询时间
	private boolean isReply = false;				//是否回复（0未回复1回复）
	private String reply = "";						//回复内容
	private Date replyTime = new Date();		//回复时间
	
	
	public Integer getConsultID() {
		return consultID;
	}
	public void setConsultID(Integer consultID) {
		this.consultID = consultID;
	}
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getConsultTime() {
		return consultTime;
	}
	public void setConsultTime(Date consultTime) {
		this.consultTime = consultTime;
	}
	public boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(boolean isReply) {
		this.isReply = isReply;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
}
