package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO {

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
