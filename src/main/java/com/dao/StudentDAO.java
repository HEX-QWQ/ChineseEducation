package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Bean.Student;
import com.DBUtils.DBUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class StudentDAO {

    public JSONArray getAllStudents() {


        JSONArray jsonResult = new JSONArray();

        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM Student";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            while (resultSet.next()) {

                JSONObject StudentObj = new JSONObject();
                StudentObj.put("account", resultSet.getString("Account"));
                StudentObj.put("pwd", resultSet.getString("Pwd"));
                StudentObj.put("sname", resultSet.getString("Sname"));
                StudentObj.put("nick", resultSet.getString("Nick"));
                StudentObj.put("birthday", resultSet.getString("Birthday"));
                StudentObj.put("time", resultSet.getString("Time"));
                StudentObj.put("vn", resultSet.getString("Vn"));
                jsonResult.add(StudentObj);
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

    public boolean SignUP(Student user) {
        //TODO: 返回false 手机号已存在，注册失败
        //TODO: 返回true 注册成功，插入数据库


        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM Student where Account='"+user.getAccount()+"'";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            if(resultSet.next()) return false;
            //说明手机号已经被注册
            System.out.println("学生姓名为"+user.getSname());

            sql = "insert into Student values(?,?,?,?,?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getAccount());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getSname());
            ps.setString(4,user.getNick());
            ps.setDate(5,user.getBirthday());
            ps.setInt(6,user.getTime());
            ps.setInt(7,user.getVn());
            int row = ps.executeUpdate();
            System.out.println("学生姓名为："+user.getSname());
            if(row>0){
                System.out.println("成功添加了" + row + "条数据！");
                return true;
            }
            // 关闭资源
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public String LogIn(Student user) {
        //TODO: 返回false 手机号已存在，注册失败
        //TODO: 返回true 注册成功，插入数据库

        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM Student where Account='"+user.getAccount()+"'";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            if(resultSet.next() == false) return "Null";
            //说明该手机号还没有被注册

            sql = "SELECT * FROM Student where Account='"+user.getAccount()+"' and Pwd='"+user.getPwd()+"'" ;
            System.out.println(sql);

            stmt = conn.createStatement();

            resultSet = stmt.executeQuery(sql);

            if(resultSet.next() == false) return "InvalidPassword";
            // 关闭资源
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "InternalError";
            //内部错误
        }
        return "Success";
    }
    public Student getInfoByAccount(String account) {
        Student user = new Student();
        user.setAccount(account);



        try {
            // 创建数据库连接
            Connection conn = DBUtils.getConnection();


            // 创建 SQL 查询语句
            String sql = "SELECT * FROM Student where Account='"+user.getAccount()+"'";

            // 创建执行 SQL 的 Statement 对象
            System.out.println(sql);
            Statement stmt = conn.createStatement();

            // 执行查询并获取结果集
            ResultSet resultSet = stmt.executeQuery(sql);

            // 遍历结果集并封装为 JSON 对象
            if(resultSet.next()) {

                user.setBirthday(resultSet.getDate("Birthday"));
                user.setNick(resultSet.getString("Nick"));
                user.setPwd(resultSet.getString("Pwd"));
                user.setSname(resultSet.getString("Sname"));

                user.setTime(resultSet.getInt("Time"));
                user.setVn(resultSet.getInt("Vn"));
            }
            System.out.println("学生姓名: "+user.getSname());
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //内部错误
        }

        return user;
    }
    public void deleteStudent(String account) throws SQLException {
        Connection conn = DBUtils.getConnection();
        // 创建 SQL 查询语句
        String sql = "DELETE FROM Student where Account='"+account+"'";

        // 创建执行 SQL 的 Statement 对象
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        int row = stmt.executeUpdate(sql);
        System.out.println("删除了"+String.valueOf(row)+"行");
    }
    public void insetStudent(Student student) throws SQLException {
        Connection conn = DBUtils.getConnection();
        // 创建 SQL 查询语句
        String sql = "INSERT INTO student (Account, Pwd, Sname, Nick, Birthday) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 创建执行 SQL 的 Statement 对象
        preparedStatement.setString(1,student.getAccount());
        preparedStatement.setString(2, student.getPwd());
        preparedStatement.setString(3, student.getSname());
        preparedStatement.setString(4, student.getNick());
        preparedStatement.setDate(5, student.getBirthday());


        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("添加成功，加入了"+String.valueOf(rowsAffected )+"行");
    }
    public void updateStudent(Student student) throws SQLException {
        Connection conn = DBUtils.getConnection();
        String sql = "UPDATE student SET Pwd = ?, Sname = ?,Nick =?, Birthday=? WHERE Account = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(5,student.getAccount());
        preparedStatement.setString(1, student.getPwd());
        preparedStatement.setString(2, student.getSname());
        preparedStatement.setString(3, student.getNick());
        preparedStatement.setDate(4, student.getBirthday());

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println("修改成功，修改了"+String.valueOf(rowsAffected )+"行");

    }
}
