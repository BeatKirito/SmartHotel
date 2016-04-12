package com.smtl.fweb.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.smtl.fweb.damain.CusConsult;
import com.smtl.fweb.damain.CusConsultWN;

public interface ConsultManage {

	/**
	 * 用户发送咨询
	 * @param 		咨询信息类
	 * @return		是否发送成功
	 */
	boolean SendMassage(CusConsult cc);
	
	/**
	 *	获取所有未回复的咨询
	 * @return
	 */
	List<CusConsultWN> GetNoReplyConsult();
	
	/**
	 * 展示所有未回复的咨询
	 * @param out
	 * @throws IOException 
	 */
	void ShowNoReplyConsult(JspWriter out) throws IOException;
	
	/**
	 * 添加回复内容
	 */
	int insertReply(CusConsult cc);
	
	/**
	 * 根据咨询ID获取咨询信息
	 * @param consultID
	 * @return
	 */
	CusConsult getConsult(int consultID);
	
	/**
	 * 根据房号获取咨询信息
	 * @param roomID
	 * @return
	 */
	List<CusConsultWN> GetConsultByRoomID(int roomID);
}
