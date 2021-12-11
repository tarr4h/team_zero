package com.th.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.th.member.model.dao.ListDao;

/**
 * Servlet implementation class DeleteListServlet
 */
@WebServlet("/th/deleteList")
public class DeleteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao lDao = new ListDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = lDao.deleteList();
		
		String msg = "";
		if(result > 0) {
			msg = "삭제 되었습니다.";
		} else {
			msg = "삭제되지 않았습니다. 등록된 데이터가 없습니다.";
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(msg, response.getWriter());
		
	}

}
