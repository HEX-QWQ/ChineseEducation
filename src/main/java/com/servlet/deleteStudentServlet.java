package com.servlet;

import com.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/deleteStudentServlet")
public class deleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析

        String account = req.getParameter("account");
        StudentDAO studentDAO = new StudentDAO();

        try {
            studentDAO.deleteStudent(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
