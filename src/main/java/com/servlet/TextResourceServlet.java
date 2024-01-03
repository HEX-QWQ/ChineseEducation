package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.dao.TextResourceDAO;

/**
 * Servlet implementation class TextResourceServlet
 */
@WebServlet("/TextResourceServlet")
public class TextResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		System.out.println("method="+method);
		if(method.equals("QUERY_ALL_RESOURCE")) {

			TextResourceDAO textResourceDAO = new TextResourceDAO();
	        JSONArray poems = textResourceDAO.getPoems();
	        JSONArray articles = textResourceDAO.getArticle();
	        JSONArray compositions = textResourceDAO.getComposition();
	        JSONArray cultures = textResourceDAO.getCulture();


	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        JSONObject combinedJson = new JSONObject();
	        combinedJson.put("poems",poems);
	        combinedJson.put("articles",articles);
	        combinedJson.put("compositions",compositions);
	        combinedJson.put("cultures",cultures);
	        

	        PrintWriter out = response.getWriter();
	        out.print(combinedJson.toJSONString());
	        out.flush();
		}
	}

}
