package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.PdsService;

public class PdsRemoveAction implements Action {

	public PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/reomove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		try {
			//DB내용 삭제
			pdsService.remove(pno);
			return url;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
