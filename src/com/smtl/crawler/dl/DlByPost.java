package com.smtl.crawler.dl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
/**
 * Post方法下载数据
 * @author
 */
public class DlByPost extends DlBase {

	@Override
	public String getResponse() {
		
		String result = "";
		BufferedReader reader = null;
		PrintWriter out = null;
		
		//检查URL是否为空
		if(!checkUrl()) return "";
		
		try {
			
			//实例化URL对象
			URL realUrl = new URL(getUrl());
			
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
			
			//发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//有请求参数时，发送请求参数
			if(!getParam().equals("")) {
				
				//获取Connection对象的输出流
				out = new PrintWriter(connection.getOutputStream());
				
				//发送请求参数
				out.print(getParam());
				
				//flush输出流的缓冲
				out.flush();
			}
			
			
			//实际下载内容，使用BufferReader读取
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				result += line;
			}
			
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		finally {	//使用finally块关闭输入流
			try {
				if(out != null) {
					out.close();
				}
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
