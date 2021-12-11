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
 * Servlet implementation class AddSpotServlet
 */
@WebServlet("/th/addSpot")
public class AddSpotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao lDao = new ListDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String area = request.getParameter("area");
		
		System.out.println("date, area : "+date+", "+area);
		
		int bfResult = lDao.deleteGameInfo();
		
		int result = 0;
		result = lDao.insertGameInfo(date, area);
		
		String msg = "";
		if(result > 0) {
			msg = "등록 되었습니다.";
		} else {
			msg = "등록되지 않았습니다. 관리자 문의";
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(msg, response.getWriter());
	}

}
