package com.jsp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;


public class LoginCheckFilter implements Filter {
	
	private List<String> exURLs = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("exclude");

		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while (st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());
		}		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		// 제외할 url 확인
		String command = httpReq.getRequestURI()
						.substring(httpReq.getContextPath().length());
						
		if(excludeCheck(command)) {
			chain.doFilter(request, response);
			return;
		}
			
		HttpSession session = httpReq.getSession();
		
		// login check
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser==null) { // 비로그인 상태
			httpResp.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpResp.getWriter();
			out.println("<script>");
			out.println("alert('로그인은 필수입니다.');");
			out.println("location.href='"+httpReq.getContextPath()+"/common/loginForm.do';");
			out.println("</script>");			
			out.close();
			
		}else { //로그인 상태.
			chain.doFilter(request, response);
		}
		
	}
	public void destroy() {}

	
	private boolean excludeCheck(String url) {
		boolean result = false;

		result = result || url.length() <= 1;

		for (String exURL : exURLs) {
			result = result || url.contains(exURL);
		}
		return result;
	}
}
