package com.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.ExamDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ExamManagerServlet")
public class ExamManagerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析

        String JSONData = req.getParameter("IdArray");
        String exam_id = req.getParameter("exam_id");
        String exam_name = req.getParameter("exam_name");

        System.out.println(JSONData);
        System.out.println(exam_id);
        System.out.println(exam_name);
        List<Integer>IdList = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(JSONData);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            IdList.add(id);
        }
        ExamDAO examDAO = new ExamDAO();

        try {
            examDAO.addExam(exam_id,exam_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            examDAO.addProb(exam_id,exam_name,IdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
