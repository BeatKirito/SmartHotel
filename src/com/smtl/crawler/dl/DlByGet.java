package com.smtl.crawler.dl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Get方法下载数据
 * @author 陈少波
 */
public class DlByGet extends DlBase {

	@Override
	public String getResponse() {
		String result = "";
		BufferedReader reader = null;
		
		//检查URL是否为空
		if(!checkUrl()) return "";
		String fullUrl = getParam().equals("") ?  getUrl() : getUrl() + "?" + getParam();
		
		try {
			
			//实例化URL对象
			URL realUrl = new URL(fullUrl);
			
			//打开URL的连接
			URLConnection connection = realUrl.openConnection();
			
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
			"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
			
			//设置用户自定义的请求报头属性
			Map<String, String> requestHeader = getRequestHeader();
			for(String key : requestHeader.keySet()) {
				connection.setRequestProperty(key, requestHeader.get(key));
			}
			
			//实际下载内容，使用BufferReader读取,使用指定编码
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),getEncoding()));
			String line;
			while((line = reader.readLine()) != null) {
				result += line;
			}
			
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		finally {	//使用finally块关闭输入流
			try {
				if(reader != null) {
					reader.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}

}
