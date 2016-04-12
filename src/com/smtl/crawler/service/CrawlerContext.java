package com.smtl.crawler.service;

/**
 * 爬虫策略工厂
 * @author beat
 *
 */
public class CrawlerContext {

	CrawlerBase cb = null;
	
	//根据策略参数，实例化不同的爬虫策略
	public CrawlerContext(String method) {
		
		switch (method) {
		case "ticket":
			cb = new TicketCrawler();
			break;
		default:
			cb = new TicketCrawler();
			break;
		}
	}
	
	//爬虫策略接口
	public void getResult(Object object) {
		cb.getResult(object); 
	}
}
