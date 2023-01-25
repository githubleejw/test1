package com.jsp.action.common;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.dto.MenuVO;

public class SubMenuAction implements Action {

	private MenuService menuService;
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		String mCode = request.getParameter("mCode");
		
		List<MenuVO> subMenu = menuService.getSubMenuList(mCode);
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.setContentType(("application/json;charset=utf-8"));
		PrintWriter out = response.getWriter();
		
		out.println(mapper.writeValueAsString(subMenu));
		
		out.close();
		
		return url;
	}
	
}
