package com.smtl.fweb.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.smtl.fweb.damain.Room;
import com.smtl.fweb.damain.RoomOrder;
import com.smtl.fweb.damain.RoomWT;

public interface RoomManage {

	/**
	 * 顾客登陆时，验证密码
	 * @param room
	 * @return
	 */
	boolean validatePassword(RoomWT room);
	
	/**
	 * 获取所有的房间信息
	 * @return
	 */
	List<RoomWT> getAllRoom();
	
	/**
	 * 展示所有房间
	 * @throws IOException 
	 */
	void showAllRoom(JspWriter out) throws IOException;
	
	/**
	 * 展示所有可以退房的房间
	 * @param out
	 * @throws IOException
	 */
	void showAllCheckOutRoom(JspWriter out) throws IOException;
	
	/**
	 * 根据roomID得到房间信息
	 * @param RoomID
	 * @return
	 */
	Room getRoom(int RoomID);
	
	/**
	 * 更新房间信息
	 * @param room
	 */
	void updateRoom(Room room);
	
	
}
