package com.servlet;

import com.Bean.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/updateStudentServlet")
public class updateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析
        String JSONData = req.getParameter("data");
        System.out.println(JSONData);
        Student student = new Student();
        JSONObject jsonObject = JSON.parseObject(JSONData);

        student.setAccount(jsonObject.getString("account"));
        java.util.Date utilDate = jsonObject.getDate("birthday");
        Date sqlDate = new Date(utilDate.getTime());
        student.setBirthday(sqlDate);
        student.setSname(jsonObject.getString("name"));
        student.setTime(jsonObject.getInteger("time"));
        student.setPwd(jsonObject.getString("pwd"));
        student.setNick(jsonObject.getString("nick"));

        StudentDAO studentDAO = new StudentDAO();

        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
