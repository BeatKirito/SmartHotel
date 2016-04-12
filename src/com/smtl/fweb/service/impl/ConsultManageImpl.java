package com.smtl.fweb.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.smtl.fweb.damain.CusConsult;
import com.smtl.fweb.damain.CusConsultWN;
import com.smtl.fweb.dao.CusComsultDao;
import com.smtl.fweb.dao.impl.CusComsultDaoImpl;
import com.smtl.fweb.service.ConsultManage;

public class ConsultManageImpl implements ConsultManage{

	private CusComsultDao cusComsultDao; 					//获取客户咨询dao
	
	public CusComsultDao getCusComsultDao() {
		return cusComsultDao;
	}

	public void setCusComsultDao(CusComsultDao cusComsultDao) {
		this.cusComsultDao = cusComsultDao;
	}

	@Override
	public boolean SendMassage(CusConsult cc) {
		
		//将咨询保存到数据库中
		if(cusComsultDao.save(cc)>0)
			return true;
		else return false;
	}

	@Override
	public List<CusConsultWN> GetNoReplyConsult() {
		//获取所有的未回复的咨询
		
		List<CusConsultWN> consultList = new ArrayList<CusConsultWN>();
		//CusComsultDao ccd = new CusComsultDaoImpl();
		consultList = cusComsultDao.findByPage(0, 5, false, null, null, null);
		//consultList = cusComsultDao.findAllNoReply();
		
		if(consultList==null )
		{
			return null;
		}
		else return consultList;
	}

	@Override
	public void ShowNoReplyConsult(JspWriter out) throws IOException {
		//展示所有未回复的咨询
		
		//获取到所有未回复的咨询
		List<CusConsultWN> consultList = new ArrayList<CusConsultWN>();
		consultList = GetNoReplyConsult();
		
		out.print("<table>");
		out.print("<tr><td>房间号</td><td>咨询时间</td><td>咨询内容</td>");
		
		if(consultList.size()==0){
			out.print("暂无用户咨询！");
			return;
		}
		
		
		for(int t=0;t<consultList.size();t++){
			
			out.print(String.format("<tr><td> <a href='consultReply.jsp?consultID=%d&content=%s&consultTime=%s&roomNum=%s'>%s </a></td><td> %s </td><td> %s</td><td><a href='consultReply.jsp?consultID=%d&content=%s&consultTime=%s&roomNum=%s'>%s</a></td>"
					,consultList.get(t).getConsultID(),
					consultList.get(t).getContent(),
					consultList.get(t).getConsultTime(),
					consultList.get(t).getRoomNum(),
					consultList.get(t).getRoomNum(),
					consultList.get(t).getConsultTime(),
					consultList.get(t).getContent(),
					consultList.get(t).getConsultID(),
					consultList.get(t).getContent(),
					consultList.get(t).getConsultTime(),
					consultList.get(t).getRoomNum(),
					"回复"));
			out.print("</tr>");
			
		}
		out.print("</table>");
	}

	@Override
	public int insertReply(CusConsult cc) {
		
		//更新回复
		cusComsultDao.update(cc);
		
		return 1; 

	}

	@Override
	public CusConsult getConsult(int consultID) {
		//获取咨询信息
		
		CusConsult cc = new CusConsult();
		cc=cusComsultDao.get(consultID);
		
		if(cc.getConsultID()!=null)
			return cusComsultDao.get(consultID);
		else return null;
	}

	//根据房间号获取到房间的咨询
	@Override
	public List<CusConsultWN> GetConsultByRoomID(int roomID) {
		
		List<CusConsultWN> ccWN = cusComsultDao.findAll();
		
		List<CusConsultWN> returnCCWN = new ArrayList<CusConsultWN>();
		
		for(int t=0;t<ccWN.size();t++){
			
			if(ccWN.get(t).getRoomID()==roomID){
				returnCCWN.add(ccWN.get(t));
			}
		}
		return returnCCWN;
	}

	
}
