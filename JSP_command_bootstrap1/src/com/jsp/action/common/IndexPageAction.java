package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class IndexPageAction implements Action {
	
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/common/indexpage";
		
		String mcode = request.getParameter("mCode");
		if(mCode == null) mCode="M000000";
		
		List<MenuVO> menuList = menuService.getMainMenuList();
		
		MenuVO menu = menuService.getMenuByMcode(mCode);
		
		requ
		
		return url;
	}

}
