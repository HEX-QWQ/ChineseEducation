package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.Student;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.CourseDAO;
import com.dao.StudentDAO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析
		Student user = new Student();
		StudentDAO studentDAO = new StudentDAO();
		user.setAccount(request.getParameter("account"));
		user.setPwd(request.getParameter("password"));
		System.out.println("账号为："+user.getAccount());
		System.out.println("密码为："+user.getPwd());
		String result = studentDAO.LogIn(user);

		if(result.equals("Success")) {
			//即将返回登录成功状态，补全Student的信息
			user = studentDAO.getInfoByAccount(user.getAccount());
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", result);

		JSONObject userObject = new JSONObject();

		userObject = (JSONObject) JSONObject.toJSON(user);

		jsonObject.put("Student", userObject);

		String jsonString = jsonObject.toJSONString();
		// 设置允许的请求方法
		response.setHeader("Access-Control-Allow-Methods", "POST");

		// 设置允许的请求头
		response.setHeader("Access-Control-Allow-Headers", "*");

		// 设置允许的来源
		response.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		System.out.print(jsonString);

		out.print(jsonString);
		out.flush();
	}

}
