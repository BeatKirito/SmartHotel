package com.smtl.crawler.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smtl.crawler.domain.ft.CType;
import com.smtl.crawler.domain.ft.PType;
import com.smtl.crawler.domain.ft.Route;
import com.smtl.crawler.service.CrawlerContext;

/**
 * Servlet implementation class FlightTicketCralwer
 */
public class FlightTicketCralwer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightTicketCralwer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		Gson gson = null;
		CrawlerContext cc = new CrawlerContext("ticket");
		
		//第一步：包装请求参数
		Route route =  packReParam(request);
		
		
		//第二步：使用爬虫业务，处理请求参数
		if(route != null) {
			cc.getResult(route); 
		}
		
		
		//第三步：输出经过处理的route对象（JSON格式）
		try {
			
			out = response.getWriter();
			
			//使用GsonBuilder来控制时间格式
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
			
			//输出route对象的Json格式
			if(route != null) {
				out.print(gson.toJson(route));
			}
			else {
				out.print("{'error' : '服务器出错'}");
			}
			
		} catch (Exception e) {
			System.out.println("Json数据输出错误：" + e);
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
		}
		
	}
	
	/**
	 * 获取并包装客户端的请求参数
	 * @param request
	 * @return	返回被包装后的机票路线对象
	 */
	private Route packReParam(HttpServletRequest request) {
		
		Route route = new Route();
		
		try {
			
			String reJsonString = request.getParameter("json");
			if(reJsonString == null) return null;
			@SuppressWarnings("unchecked")
			Map<Object, Object> jsonObject = gson.fromJson(reJsonString, Map.class);
			
			String dCity = jsonObject.get("dCity").toString();
			String aCity = jsonObject.get("aCity").toString();
			
			Pattern p = Pattern.compile("\\|\\|(\\w{3}?)");
			Matcher md = p.matcher(dCity);
			Matcher ma = p.matcher(aCity);
			
			if(!md.find() || !ma.find()) return null;
			
			route.setdCity(md.group(1).toUpperCase()); 
			route.setaCity(ma.group(1).toUpperCase());
			
			//出发时间的验证
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String timeString = jsonObject.get("dDate").toString();
			
			p = Pattern.compile("(\\d{4}?)-(\\d{2}?)-(\\d{2}?)");
			Matcher m = p.matcher(timeString);
			if(!m.find()) {
				return null;
			}
			
			int year = Integer.parseInt(m.group(1));
			int month = Integer.parseInt(m.group(2));
			int day = Integer.parseInt(m.group(3));
			int nowYear = Calendar.getInstance().get(Calendar.YEAR);
			
			if(year < nowYear || year > nowYear + 5 ) return null;
			if(month < 1 || month > 12) return null;
			switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if(day < 1 || day > 31) return null;
				break;
			case 2:
				if(day <  1 || day > 29) return null;
			default:
				if(day < 1 || day > 30) return null;
				break;
			}
			route.setdDate(sdf.parse(timeString));
			route.setpNum(Integer.parseInt( jsonObject.get("num").toString()));
			
			String pType = jsonObject.get("pType").toString();
			
			switch (pType) {
			case "adu":
				route.setpType(PType.ADU); 
				break;
			case "chi":
				route.setpType(PType.CHI); 
				break;
			case "bab":
				route.setpType(PType.BAB); 
				break;
			default:
				route.setpType(PType.ADU);  //默认设置为成人票
				break;
			}
			
			String cType =  jsonObject.get("cType").toString();
			
			switch (cType) {
			case "y":
				route.setcType(CType.Y);
				break;
			case "cf":
				route.setcType(CType.CF); 
				break;
			default:
				route.setcType(CType.Y); //默认设置为经济舱
				break;
			}
			
			
		} catch (Exception e) {
			System.out.println("请求参数包装出错：" + e);
			e.printStackTrace();
			return null;
		}
		
		return route;
	}

}
