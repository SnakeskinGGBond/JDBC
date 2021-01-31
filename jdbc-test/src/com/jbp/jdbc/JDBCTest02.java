package com.jbp.jdbc;
/**
 * JDBC完成delect
 */

import java.sql.*;

public class JDBCTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            //注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/jbp?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
            String user = "root";
            String password = "jxp602190516";
            conn = DriverManager.getConnection(url, user, password);
            //获取数据库操作对象
            statement = conn.createStatement();
            //执行sql语句
            String sql = "delete from dept where deptno = 50";
            int i = statement.executeUpdate(sql);
            System.out.println(i == 1 ? "执行成功！" : "执行失败！");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
