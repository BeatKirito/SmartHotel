package com.smtl.crawler.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.smtl.crawler.dl.DlContext;
import com.smtl.crawler.domain.ft.CType;
import com.smtl.crawler.domain.ft.Flight;
import com.smtl.crawler.domain.ft.PType;
import com.smtl.crawler.domain.ft.Route;
import com.smtl.crawler.domain.ft.Ticket;

public class TicketCrawler extends CrawlerBase {
	
	private static String url = 
			"http://flights.ctrip.com/domesticsearch/search/SearchFirstRouteFlights";  
	
	private static String pxRefer = "http://flights.ctrip.com/booking/";
	
	private static String host = "flights.ctrip.com";
	
	private static String acceptEncoding = "gzip";
	
	private static String cookie = " _abtest_userid=cbf06cc6-e8a8-40e3-b71f-cf0627227128;"
			+ " _bfa=1.1432260020392.z81qr.1.1433644078825.1433679952114.5.66;"
			+ " _jzqco=%7C%7C%7C%7C1433679952683%7C1.576607901.1432260020646"
			+ ".1433687679829.1433687683307.1433687679829.1433687683307.undefined.0.0.50.50;"
			+ " __zpspc=9.6.1433683791.1433687683.16%234%7C%7C%7C%7C%7C%23; appFloatCnt=1;"
			+ " manualclose=1; zdatactrip=zdatactrip=e3e810a3190cbc7492968e2e384b8b45;"
			+ " _ga=GA1.2.584449237.1432299236;"
			+ " FD_SearchHistorty={\"type\":\"D\",\"data\":\"D%24%u6DF1%u5733%28SZX%29%24SZX"
			+ "%242015-06-07%24%u5317%u4EAC%28BJS%29%24BJS%242015-06-10\"}; "
			+ "DomesticUserHostCity=CAN|%b9%e3%d6%dd; zdata=zdata=JwlomCfSYOYvafcQyPy7smniVi0=;"
			+ " bid=bid=F; ASP.NET_SessionSvc=MTAuMTUuMTM2LjMyfDkwOTB8b3V5YW5nfGRlZmF1bHR8MTQzMjIwMTM0MjMxNw;"
			+ " _bfs=1.49; _bfi=p1%3D101029%26p2%3D101029%26v1%3D66%26v2%3D65; Customer=HAL=language_gb; _ctm_t=ctrip";
	
	//下载策略
	private DlContext dlContext = new DlContext("get",true);

	@Override
	public void getResult(Object object) {
		
		//第一步：获取route对象
		Route route = (Route)object;
		
		//第二步：解析Route对象，设置请求参数和报头数据，初始化数据下载策略
		initDL(route);
		
		//第三步：开始下载，接收响应数据
		String resultJson = dlContext.getResponse();
		
		System.out.println("相应的JSON数据如下：");
		System.out.println(resultJson);
		
		//第四步，分析数据
		analyze(resultJson, route);
		
	}
	
	/**
	 * 初始化下载参数，准备数据的下载
	 * @param route	机票路线请求数据（请求数据）
	 */
	private void initDL(Route route) {
		
		//第一步：设置下载策略的URL属性
		dlContext.setUrl(url);
		
		//第二步：设置下载策略的请求报头
		String referer = "";
		referer = pxRefer + route.getdCity().toUpperCase()
				+ "-" + route.getaCity().toUpperCase() + "-day-"
				+ route.getpNum() + ".html";
		
		Map<String, String> reHeaders = dlContext.getRequestHeader();
		reHeaders.put("host", host);
		reHeaders.put("Accept-Encoding",acceptEncoding);	//这个请求报头获得的数据被压缩，乱码
		reHeaders.put("Referer", referer);
		reHeaders.put("Cookie", cookie);
		
		//第三步：设置请求参数
		StringBuilder sb = new StringBuilder("");
		String param = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("DCity1=" + route.getdCity().toUpperCase());
		sb.append("&ACity1=" + route.getaCity().toUpperCase());
		sb.append("&SearchType=S");	//暂时定为查询单程机票
		sb.append("&DDate1=" +sdf.format(route.getdDate()));
		
		//添加乘客类型以及舱位等级的请求参数
		if(route.getpType() != PType.ADU) {
			switch (route.getpType()) {
			case CHI:
				sb.append("&PassengerType=CHI");
				break;
			case BAB:
				sb.append("&PassengerType=BAB");
				break;
			default:
				break;
			}
		}
		
		if(route.getcType() == CType.CF ) {
			sb.append("&ClassType=CF");
		}
		
		param = sb.toString();
		System.out.println("请求参数：" + param);
		
		dlContext.setParam(param); 
		
		//第四步：设置响应信息的编码
		dlContext.setEncoding("gb2312"); 
	}
	
	/**
	 * 分析获得的机票的Json数据，包装到POJO中
	 * @param resultJson
	 * @param route
	 */
	@SuppressWarnings("unchecked")
	private void analyze(String resultJson, Route route) {
		
		Gson gson = new Gson();
		Map<String, Object> routeMap = null;
		
		//第一步：获取机票路线映射对象
		routeMap = (Map<String, Object>)gson.fromJson(resultJson, Map.class);
		
		//第二步：获取航班列表和机场列表
		Map<String, String> airLines = (Map<String, String>)routeMap.get("als");
		Map<String, String> airPorts = (Map<String,String>)routeMap.get("apb");
		
		//第三步：获取机票航班列表
		List<Map<String, Object>> flights
		= (List<Map<String,Object>>)routeMap.get("fis");
		
		if(flights.size() == 0) return;
		
		//第四步：遍历每一项航班，包装航班数据
		route.setFlights(new LinkedList<Flight>());
		for(Map<String, Object> flightMap : flights) {
			
			Flight flight = new Flight();
			
			//设置航空公司和航空公司代码
			flight.setAlCode((String)flightMap.get("alc"));
			flight.setAirLine(airLines.get(flight.getAlCode()));
			
			//设置航班代码
			flight.setFlightNo((String)flightMap.get("fn"));
			
			//设置计划机型代码和大小
			Map<String, String> cf = (Map<String, String>)flightMap.get("cf");
			flight.setFt(cf.get("c"));
			switch (cf.get("s")) {
			case "S":
				flight.setFtSize('小');
				break;
			case "M":
				flight.setFtSize('中');
				break;
			case "L":
				flight.setFtSize('大');
				break;
			default:
				flight.setFtSize('\0');
				break;
			}
			
			//设置机场地点
			String dpc = (String)flightMap.get("dpc") + (int)(double)(flightMap.get("dbid"));
			flight.setdAirPort(airPorts.get(dpc));
			
			String apc = (String)flightMap.get("apc") + (int)(double)(flightMap.get("abid"));
			flight.setaAirPort(airPorts.get(apc));
			
			//设置出发和到达的具体时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				flight.setdTime(sdf.parse((String)flightMap.get("dt")));
				flight.setaTime(sdf.parse((String)flightMap.get("at")));
				
			} catch (Exception e) {
				System.out.println("时间设置异常" + e);
				e.printStackTrace();
			}
			
			//其余杂项
			flight.setPr((double)flightMap.get("pr"));
			flight.setCate(false);		//由于还未找到该数据对于的Json的数据，暂时默认为无
			flight.setTax((double)flightMap.get("tax"));
			flight.setYp((double)flightMap.get("yp"));
			
			//设置起价
			flight.setLowPrice((double)flightMap.get("lp"));
			
			//设置具体机票
			List<Map<String, Object>> tickets
			= (List<Map<String,Object>>)flightMap.get("scs");
			
			if(tickets == null) return;
			
			flight.setTickets(new LinkedList<Ticket>());
			for(Map<String,Object> ticketMap : tickets) {
				
				Ticket ticket = new Ticket();
				
				//设置票价
				ticket.setPrice((Double)ticketMap.get("p"));
				
				//设置余量
				double temp = 0;
				if((temp = (double)ticketMap.get("mq")) != 0) {
					ticket.setSaleOut(true);
					ticket.setRemain((int)temp); 
				}
				else {
					ticket.setSaleOut(false);
				}
				
				//设置舱位等级
				String classType = ((String)ticketMap.get("c")).toUpperCase();				
				switch (classType) {
				case "F":
					ticket.setClassType("头等舱");
					break;
				case "C":
					ticket.setClassType("头等舱");
					break;
				case "Y":
					ticket.setClassType("经济舱");
					break;
				default:	//默认设置为经济舱
					ticket.setClassType("经济舱");
					break;
				}
					
				//将机票信息加入航班中
				flight.getTickets().add(ticket);
			}
			
			//将航班信息加入机票路线中
			route.getFlights().add(flight);
			
		}
	}
}
