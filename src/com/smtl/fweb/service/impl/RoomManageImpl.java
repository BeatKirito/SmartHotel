package com.smtl.fweb.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.smtl.fweb.damain.Room;
import com.smtl.fweb.damain.RoomOrder;
import com.smtl.fweb.damain.RoomWT;
import com.smtl.fweb.dao.RoomDao;
import com.smtl.fweb.dao.RoomOrderDao;
import com.smtl.fweb.dao.RoomRecordDao;
import com.smtl.fweb.service.RoomManage;

public class RoomManageImpl implements RoomManage {
	
	private RoomDao roomDao;
	private RoomRecordDao roomRecordDao;

	
	public RoomRecordDao getRoomRecordDao() {
		return roomRecordDao;
	}
	public void setRoomRecordDao(RoomRecordDao roomRecordDao) {
		this.roomRecordDao = roomRecordDao;
	}
	public RoomDao getRoomDao() {
		return roomDao;
	}
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	
	@Override
	public boolean validatePassword(RoomWT room) {
		
		RoomWT tRoom = roomDao.getFormRoomNum(room.getRoomNum());
		
		if(!tRoom.getPassword().equals(room.getPassword())) {
			return false;
		}else {
			room.setRoomID(tRoom.getRoomID());
			room.setRoomTypeID(tRoom.getRoomID());
			room.setRoomTypeName(tRoom.getRoomTypeName());
			room.setPrice(tRoom.getPrice());
			room.setFloor(tRoom.getFloor());
			room.setState(tRoom.getState()); 
		}
		
		return true;
	}
	@Override
	public List<RoomWT> getAllRoom() {
		
		return roomDao.findAll();
	}
	@Override
	public void showAllRoom(JspWriter out) throws IOException {
		
		List<RoomWT> roomWT = getAllRoom();
		out.print("<h3>空房列表</h3>");
		out.print("<table>");
		out.print("<tr><td></td><td>房间号</td><td>房间类型</td></tr>");
		//展示数据
		
		for(int j=0;j<6;j++){
			//out.print(String.format("<tr>%d楼", j));
			out.print("<tr>");
			for(int t=0;t<roomWT.size();t++){
				if(roomWT.get(t).getState()==false){				//只显示没有被预定的房间
					if(roomWT.get(t).getFloor()==j){
						out.print(String.format("<td></td><td><a href='ConfirmRoom.jsp?roomID=%s&roomNum=%s&roomType=%s'>%s</a></td><td>%s</td>", 
							roomWT.get(t).getRoomID(),
							roomWT.get(t).getRoomNum(),
							roomWT.get(t).getRoomTypeName(),
							roomWT.get(t).getRoomNum(),
							roomWT.get(t).getRoomTypeName()));
				}
				}
			}
			out.print("</tr>");
		}
		out.print("</table>");
	}
	
	//获取房间信息
	@Override
	public Room getRoom(int RoomID) {
		
		return roomDao.get(RoomID);
	}
	
	//更新房间信息
	@Override
	public void updateRoom(Room room) {
		
		roomDao.update(room);
		
	}
	@Override
	public void showAllCheckOutRoom(JspWriter out) throws IOException {
		
		List<RoomWT> roomWT = getAllRoom();
		out.print("<h3>退房列表</h3>");
		out.print("<table>");
		out.print("<tr><td></td><td>房间号</td><td>房间类型</td></tr>");
		//展示数据
		
		for(int j=0;j<6;j++){
			//out.print(String.format("<tr>%d楼", j));
			out.print("<tr>");
			for(int t=0;t<roomWT.size();t++){
				if(roomWT.get(t).getState()!=false){				//只显示没有被预定的房间
					if(roomWT.get(t).getFloor()==j){
						out.print(String.format("<td></td><td><a href='ConfirmCheckOutRoom.jsp?roomID=%s&roomNum=%s&roomType=%s'>%s</a></td><td>%s</td>", 
							roomWT.get(t).getRoomID(),
							roomWT.get(t).getRoomNum(),
							roomWT.get(t).getRoomTypeName(),
							roomWT.get(t).getRoomNum(),
							roomWT.get(t).getRoomTypeName()));
				}
				}
			}
			out.print("</tr>");
		}
		out.print("</table>");
		
	}
	
	

}
