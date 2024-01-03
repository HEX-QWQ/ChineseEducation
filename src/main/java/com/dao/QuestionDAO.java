package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Bean.Question;
import com.DBUtils.DBUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class QuestionDAO {

	public List<Question> getALLProb() {
		List<Question> probList = new ArrayList<>();
		Question question;

		try {
			// 创建数据库连接
			Connection conn = DBUtils.getConnection();


			// 创建 SQL 查询语句
			String sql = "select * from Question";//随机抽取五道题目

			// 创建执行 SQL 的 Statement 对象
			System.out.println(sql);
			Statement stmt = conn.createStatement();

			// 执行查询并获取结果集
			ResultSet resultSet = stmt.executeQuery(sql);

			// 遍历结果集并封装为 JSON 对象
			while (resultSet.next()) {
				question = new Question();
				question.setId(resultSet.getInt("id"));
				question.setCorrect(resultSet.getInt("Correct"));
				question.setOptionA(resultSet.getString("OptionA"));
				question.setOptionB(resultSet.getString("OptionB"));
				question.setOptionC(resultSet.getString("OptionC"));
				question.setOptionD(resultSet.getString("OptionD"));
				question.setQuestion(resultSet.getString("Question"));
				question.setTips(resultSet.getString("Tips"));
				probList.add(question);
			}

			// 关闭资源
			resultSet.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return probList;
	}
	public List<Question> getProbList() {
		List<Question> probList = new ArrayList<>();
		Question question;

		try {
			// 创建数据库连接
			Connection conn = DBUtils.getConnection();


			// 创建 SQL 查询语句
			String sql = "select * from Question order by rand() limit 5";//随机抽取五道题目

			// 创建执行 SQL 的 Statement 对象
			System.out.println(sql);
			Statement stmt = conn.createStatement();

			// 执行查询并获取结果集
			ResultSet resultSet = stmt.executeQuery(sql);

			// 遍历结果集并封装为 JSON 对象
			while (resultSet.next()) {
				question = new Question();
				question.setId(resultSet.getInt("id"));
				question.setCorrect(resultSet.getInt("Correct"));
				question.setOptionA(resultSet.getString("OptionA"));
				question.setOptionB(resultSet.getString("OptionB"));
				question.setOptionC(resultSet.getString("OptionC"));
				question.setOptionD(resultSet.getString("OptionD"));
				question.setQuestion(resultSet.getString("Question"));
				question.setTips(resultSet.getString("Tips"));
				probList.add(question);
			}

			// 关闭资源
			resultSet.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return probList;
	}
	public JSONArray getAllProb() {


		JSONArray jsonResult = new JSONArray();

		try {
			// 创建数据库连接
			Connection conn = DBUtils.getConnection();


			// 创建 SQL 查询语句
			String sql = "SELECT * FROM Question";

			// 创建执行 SQL 的 Statement 对象
			System.out.println(sql);
			Statement stmt = conn.createStatement();

			// 执行查询并获取结果集
			ResultSet resultSet = stmt.executeQuery(sql);

			// 遍历结果集并封装为 JSON 对象
			while (resultSet.next()) {
				JSONObject questionObj = new JSONObject();
				questionObj.put("id",resultSet.getString("id"));//将id也传过去
				questionObj.put("Question", resultSet.getString("Question"));
				questionObj.put("OptionA", resultSet.getString("OptionA"));
				questionObj.put("OptionB", resultSet.getString("OptionB"));
				questionObj.put("OptionC", resultSet.getString("OptionC"));
				questionObj.put("OptionD", resultSet.getString("OptionD"));
				jsonResult.add(questionObj);
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
