package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberModifyAction implements Action{

	private MemberService service;
	public void setMemberService(MemberService service) {
		this.service = service;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/member/modify_success";
		
		
		
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPicture(request.getParameter("picture"));
		member.setAuthority(request.getParameter("authority"));
		member.setPhone(request.getParameter("phone"));
		
		try {
			service.modify(member);
			request.setAttribute("member", service.getMember(member.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}
