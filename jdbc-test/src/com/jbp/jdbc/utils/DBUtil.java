package com.jbp.jdbc.utils;

import java.sql.*;

public class DBUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 工具类的构造方法一般都是私有的
     * 工具类中的方法都是静态的，不需要new对象，直接采用类名调用
     */
    private DBUtil() {
    }

    /**
     * 获取数据库连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection gertConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jbp?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true",
                "root", "jxp602190516");
    }

    /**
     * 释放资源
     * @param connection 连接对象
     * @param statement 数据库操作对象
     * @param resultSet 结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
