package com.smtl.mweb.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;

/**
 * Servlet Filter implementation class MWebFilter
 */
public class MWebFilter implements Filter {
	
	private Gson gson =  new Gson();  
	private static String[] noFilteringURIs = {	//不过滤的接口集合
		"2DCLogin.jsp","Login"
	};

    /**
     * Default constructor. 
     */
    public MWebFilter() {
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
		
		String reqURI = httpRequest.getRequestURI();
		System.out.println("reqURI: " + reqURI );
		Pattern p = Pattern.compile("/SmartHotel/([\\w.]*)");
		Matcher m = p.matcher(reqURI);
		if(m.find()) {
			for(String noFilteringURI : noFilteringURIs) {
				String temp = m.group(1);
				if(noFilteringURI.equals(m.group(1))) {
					isCheck = false;
					chain.doFilter(request, response);
				}
			}
		}
		
		if(isCheck) {
			
			HttpSession session = httpRequest.getSession();
			if(session == null || session.getAttribute("room") == null) {
				stateResult.setSuccess(false);
				stateResult.setMessage("尚未登陆");
				httpResponse.getWriter().write(gson.toJson(stateResult));
				//request.getRequestDispatcher("NoLoginPage.html"); 
				return;
			}
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
