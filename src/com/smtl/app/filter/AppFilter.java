package com.smtl.app.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.smtl.app.param.BaseParam;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;
import com.smtl.fweb.damain.SUserWT;

/**
 * Servlet Filter implementation class AppFilter
 */
public class AppFilter implements Filter {
	
	private Gson gson =  new Gson();  
	private static String[] noFilteringURIs = {	//不过滤的接口集合
		"AppUserLogin","ConnectTest"
	};

    /**
     * Default constructor. 
     */
    public AppFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		StateResult stateResult = new BaseResult();
		boolean isCheck = true;
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		CoverResponse cr = new CoverResponse(httpResponse);
		
		String reqURI = httpRequest.getRequestURI();
		System.out.println("reqURI: " + reqURI );
		Pattern p = Pattern.compile("/SmartHotel/app/(\\w*)");
		Matcher m = p.matcher(reqURI);
		if(m.find()) {
			for(String noFilteringURI : noFilteringURIs) {
				if(noFilteringURI.equals(m.group(1))) {
					isCheck = false;
					chain.doFilter(request, cr);
					String content = cr.getContent();
					System.out.println("OutPut: " + content);
					response.getWriter().write(content); 
				}
			}
		}
		
		if(isCheck) {
			
			String params = request.getParameter("params");
			BaseParam baseParam = gson.fromJson(params, BaseParam.class);
			
			if(baseParam == null || baseParam.getSsid().equals("")) {
				stateResult.setSuccess(false);
				stateResult.setMessage("用户未登录");
				String result = gson.toJson(stateResult);
				System.out.println("OutPut: " + result);
				response.getWriter().write(result);
			}else {
				
				ServletContext application =  httpRequest.getSession().getServletContext();
				Map<StateResult, SUserWT> sessionPool = 
						(Map<StateResult, SUserWT>)application.getAttribute("sessionPool");
				
				if(sessionPool == null || sessionPool.size() == 0  || sessionPool.get(baseParam.getSsid()) == null) {
					stateResult.setSuccess(false);
					stateResult.setMessage("用户未登录");
					String result = gson.toJson(stateResult);
					System.out.println("OutPut: " + result);
					response.getWriter().write(result);
				}else {
					chain.doFilter(request, cr);
					String content = cr.getContent();
					System.out.println("OutPut: " + content);
					response.getWriter().write(content); 
				}
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
