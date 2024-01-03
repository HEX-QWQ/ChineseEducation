package com.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.DBUtils.DBUtils;
import com.alibaba.fastjson.*;

public class CourseDAO {

    public JSONArray getAllCourses() {


        JSONArray jsonResult = new JSONArray();

        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM Course";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            while (resultSet.next()) {
                System.out.println("Cno="+resultSet.getString("Cno")+",Cname="+resultSet.getString("Cname"));
                JSONObject courseObj = new JSONObject();
                courseObj.put("Cno", resultSet.getString("Cno"));
                courseObj.put("Cname", resultSet.getString("Cname"));
                jsonResult.add(courseObj);
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
}
