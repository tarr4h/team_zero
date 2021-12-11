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
 * Servlet implementation class PasswordCheck
 */
@WebServlet("/th/password")
public class PasswordCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao lDao = new ListDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int password = Integer.parseInt(request.getParameter("password"));
		
		int result = lDao.checkPassword(password);
		
		response.setContentType("appliction/json; charset=utf-8");
		new Gson().toJson(result, response.getWriter());
	}

}
