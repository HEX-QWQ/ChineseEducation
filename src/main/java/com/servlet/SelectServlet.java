package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.dao.*;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
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
		if(method.equals("ALL_COURSE")) {

			CourseDAO courseDAO = new CourseDAO();
	        JSONArray courses = courseDAO.getAllCourses();


	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");


	        PrintWriter out = response.getWriter();
	        out.print(courses.toJSONString());
	        out.flush();
		}else if(method.equals("ALL_STUDENTS")){
			StudentDAO studentDAO = new StudentDAO();
			JSONArray jsonArray = studentDAO.getAllStudents();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");


			PrintWriter out = response.getWriter();
			out.print(jsonArray.toJSONString());
			out.flush();
		}else if(method.equals("SELECT_TEXT")){
			String Type = request.getParameter("Type");
			TextDAO textDAO = new TextDAO();
			JSONArray jsonArray = textDAO.getAllText(Type);
			response.setContentType("application/json");
			System.out.println(jsonArray.toJSONString());
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.print(jsonArray.toJSONString());
			out.flush();
		}else if(method.equals("SELECT_EXAM")){
			ExamDAO examDAO = new ExamDAO();
			JSONArray jsonArray = examDAO.getAllExam();
			response.setContentType("application/json");
			System.out.println(jsonArray.toJSONString());
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			System.out.println(jsonArray);
			out.print(jsonArray.toJSONString());
			out.flush();
		}else if(method.equals("SELECT_QUESTION")){
			QuestionDAO questionDAO = new QuestionDAO();
			JSONArray jsonArray = questionDAO.getAllProb();
			response.setContentType("application/json");
			System.out.println(jsonArray.toJSONString());
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			System.out.println(jsonArray);
			out.print(jsonArray.toJSONString());

		}
	}
}
