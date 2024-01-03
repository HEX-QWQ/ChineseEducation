package com.dao;

import com.DBUtils.DBUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.List;

public class ExamDAO {
    public JSONArray getAllExam() {


        JSONArray jsonResult = new JSONArray();

        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM exam";

            // 创建执行 SQL 的 Statement 对象
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            while (resultSet.next()) {
                JSONObject examObj = new JSONObject();
                examObj.put("exam_id", resultSet.getString("exam_id"));
                examObj.put("exam_date", resultSet.getString("exam_date"));
                examObj.put("exam_name", resultSet.getString("exam_name"));
                jsonResult.add(examObj);
            }

            // 关闭资源
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
    public void addExam(String Exam_id,String Exam_name) throws SQLException {
        Connection conn = DBUtils.getConnection();
        // 创建 SQL 查询语句
        String sql = "INSERT INTO exam (exam_id, exam_date, exam_name) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 创建执行 SQL 的 Statement 对象
        preparedStatement.setString(1,Exam_id);
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.setString(3, Exam_name);
        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("添加成功，加入了"+String.valueOf(rowsAffected )+"行");
    }
    public void addProb(String Exam_id, String Exam_name, List<Integer>IdList) throws SQLException {
        Connection conn = DBUtils.getConnection();
        // 创建 SQL 查询语句
        for(int i=0;i<IdList.size();i++){
            String sql = "INSERT INTO exam_question (exam_id, question_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,Exam_id);
            preparedStatement.setInt(2, IdList.get(i));
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("添加成功，加入了"+String.valueOf(rowsAffected )+"行");
        }
    }
}
