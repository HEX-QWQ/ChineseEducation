package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.DBUtils.DBUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TextResourceDAO {
	public JSONArray getResource(String type) {


		JSONArray jsonResult = new JSONArray();

		try {
			// 创建数据库连接
			Connection conn = DBUtils.getConnection();


			// 创建 SQL 查询语句
			String sql = "SELECT * FROM TextResource where Type='"+type+"'";

			// 创建执行 SQL 的 Statement 对象
			System.out.println(sql);
			Statement stmt = conn.createStatement();

			// 执行查询并获取结果集
			ResultSet resultSet = stmt.executeQuery(sql);

			// 遍历结果集并封装为 JSON 对象
			while (resultSet.next()) {
				JSONObject textResourceObj = new JSONObject();
				textResourceObj.put("Id", resultSet.getString("Id"));
				textResourceObj.put("Title", resultSet.getString("Title"));
				textResourceObj.put("Type", resultSet.getString("Type"));
				jsonResult.add(textResourceObj);
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
	public JSONArray getPoems() {
		// TODO Auto-generated method stub
		return getResource("poem");
	}

	public JSONArray getArticle() {
		// TODO Auto-generated method stub
		return getResource("article");
	}

	public JSONArray getComposition() {
		// TODO Auto-generated method stub
		return getResource("composition");
	}

	public JSONArray getCulture() {
		// TODO Auto-generated method stub
		return getResource("culture");
	}


}
