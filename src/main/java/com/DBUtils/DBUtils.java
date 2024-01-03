package com.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    static String url = "jdbc:mysql://localhost:3306/Test"; // 根据您的实际情况修改 URL
    static String username = "root"; // 根据您的实际情况修改用户名
    static String password = "123456"; // 根据您的实际情况修改密码


    public static java.sql.Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载 MySQL 驱动程序
            connection = DriverManager.getConnection(url, username, password);
            // 连接成功，进行后续操作
            System.out.println("success!");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // 连接失败，处理异常
            System.err.println("数据库连接失败：" + e.getMessage());
        } finally {

        }
        return null;
    }
}
