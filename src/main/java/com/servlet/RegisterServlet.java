package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.StudentDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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

		StudentDAO studentDAO = new StudentDAO();
		String SignUpJSON = request.getParameter("jsonData");
		System.out.println(SignUpJSON);
		Student user = fromJSON(SignUpJSON);
		System.out.println(user.getAccount());
		PrintWriter out = response.getWriter();
		if(studentDAO.SignUP(user)) {


	        out.print("success");
	        out.flush();
		}
		else {

			out.print("failed");
	        out.flush();
		}
	}
	public Student fromJSON(String SignUpJSON) {
		JSONObject jsonObject = JSON.parseObject(SignUpJSON);
		Student user = new Student();
		if(jsonObject.containsKey("account")) {
			user.setAccount(jsonObject.getString("account"));
		}
		if(jsonObject.containsKey("Sname")) {
			user.setSname(jsonObject.getString("Sname"));
		}
		if(jsonObject.containsKey("password")) {
			user.setPwd(jsonObject.getString("password"));
		}
		if(jsonObject.containsKey("username")) {
			user.setNick(jsonObject.getString("username"));
		}
		if(jsonObject.containsKey("birthday")) {
			String dateString=jsonObject.getString("birthday");
			
			String pattern = "yyyy-MM-dd";
	        

	        Date date = Date.valueOf(dateString);
			user.setBirthday(date);
		}
		return user;
	}
}
