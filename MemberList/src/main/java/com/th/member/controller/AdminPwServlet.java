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
 * Servlet implementation class AdminPwServlet
 */
@WebServlet("/th/adminPw")
public class AdminPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao lDao = new ListDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pw = Integer.parseInt(request.getParameter("password"));
		
		int result = lDao.checkAdminPassword(pw);

		response.setContentType("appliction/json; charset=utf-8");
		new Gson().toJson(result, response.getWriter());
	}

}
