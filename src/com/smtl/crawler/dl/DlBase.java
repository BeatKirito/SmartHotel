package com.smtl.crawler.dl;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据下载业务的抽象父类
 * @author 陈少波
 *
 */
public abstract class DlBase {

	//请求的URL
	private String url = "";
	
	//请求参数（格式：参数1=值1&参数2=值2）
	private String param = "";
	
	private Map<String, String> requestHeader = new HashMap<String, String>();
	
	private String encoding = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Map<String, String> getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(Map<String, String> requestHeader) {
		this.requestHeader = requestHeader;
	}
	
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 根据已有的请求属性，获取响应结果
	 * @return	返回响应的数据的输入流
	 */
	public abstract String getResponse();
	
	protected boolean checkUrl() {
		
		if(url.equals("")) {
			return false;
		}
		
		return true;
	}
	
	
}
