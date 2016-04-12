package com.smtl.crawler.dl;

import java.util.Map;

/**
 * 数据下载方法的策略工厂
 * @author beat
 */
public class DlContext {

	private DlBase dl = null;
	
	public DlContext(String method,boolean isGzip) {
		
		method = method.toLowerCase();
		
		switch (method) {
		case "get":
			if(isGzip) {
				dl = new DlByGetWithUC();
			}else {
				dl = new DlByGet();
			}
			break;
			
		case "post":
			dl = new DlByPost();
			break;

		default:	//若构造函数参数不正确，则默认实例化get方法的下载策略
			dl = new DlByGet();
			break;
		}
	}
	
	//设置url属性
	public void setUrl(String url) {
		if(url != null) {
			dl.setUrl(url);
		}
	}
	
	//设置请求参数属性
	public void setParam(String param) {
		if(param != null) {
			dl.setParam(param);
		}
	}
	
	//设置响应信息编码
	public void setEncoding(String encoding) {
		if(encoding != null) {
			dl.setEncoding(encoding); 
		}
	}
	
	//获取请求报头实例
	public Map<String, String> getRequestHeader() {
		return dl.getRequestHeader();
	}
	
	//设置请求报头
	public void setRequestHeader(Map<String, String> requestHeader) {
		if(requestHeader != null) {
			dl.setRequestHeader(requestHeader); 
		}
	}
	
	//策略调用
	public String getResponse() {
		return dl.getResponse();
	}
}
