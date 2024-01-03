package com.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.DBUtils.DBUtils;
import com.alibaba.fastjson.*;

public class TextDAO {

    public JSONArray getAllText(String Type) {


        JSONArray jsonResult = new JSONArray();

        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM textresource where Type='"+Type+"'";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            while (resultSet.next()) {
                JSONObject TextObj = new JSONObject();
                TextObj.put("Id", resultSet.getString("Id"));
                TextObj.put("Title", resultSet.getString("Title"));
                jsonResult.add(TextObj);
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
