package com.smtl.crawler.service;

/**
 * 爬虫策略的父类
 * （不设置抽象是为了让容纳和扩展更多样的爬虫策略）
 * @author beat
 *
 */
public class CrawlerBase {

	//当前需要的接口
	public void getResult(Object object) {
		
	}
	
	//以下可以扩展其他爬虫策略接口
	public Object getOther(Object object) {
		return null;
	}
}
