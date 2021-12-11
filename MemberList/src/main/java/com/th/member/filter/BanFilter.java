package com.th.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BanFilter
 */
@WebFilter(urlPatterns = {"/th/addList", "/th/deleteMember", "/th/playarea", "/th/deleteList", "/th/calculate", "/th/password", "/th/listPage", "/th/getList", "/th/adminPw", "/th/admin", "/th/addSpot" })

public class BanFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BanFilter() {
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
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpServletResponse httpResponse = (HttpServletResponse) response;
		  
		 String referer = httpRequest.getHeader("referer");
		 System.out.println("referer : "+referer);
		  
		 if(referer == null) {
		  httpResponse.sendRedirect(httpRequest.getContextPath()+"/"); return;
		 }
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
