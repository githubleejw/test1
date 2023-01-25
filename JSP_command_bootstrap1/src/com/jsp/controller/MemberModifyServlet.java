package com.jsp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.context.ApplicationContext;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;


public class MemberModifyServlet extends HttpServlet {

	private MemberService service;// = new MemberServiceImpl();
	{
		Map<String,Object> container = ApplicationContext.getApplicationContext();
		service = (MemberService)container.get("memberService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/member/modify.jsp";

		String id = request.getParameter("id");
		try {
			MemberVO member = service.getMember(id);
			request.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url="detail?id=";
		
		//request.setCharacterEncoding("utf-8");
		
		url+=request.getParameter("id");
		
		String[] phoneArray=request.getParameterValues("phone");
		String phone = phoneArray[0]+phoneArray[1]+phoneArray[2];
		
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPicture(request.getParameter("picture"));
		member.setAuthority(request.getParameter("authority"));
		member.setPhone(phone);
		try {
			service.modify(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(url);
	}

}

