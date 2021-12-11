package com.th.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.th.member.model.dao.ListDao;

/**
 * Servlet implementation class GetGameInfoServlet
 */
@WebServlet("/th/playarea")
public class GetGameInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao lDao = new ListDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> info = lDao.getGameInfo();
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(info, response.getWriter());
	}

}
