package com.jbp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC完成update
 */
public class JDBCTest20 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbp?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&&useSSL=true",
                    "root", "jxp602190516");
            //获取数据库操作对象
            statement = conn.createStatement();
            //执行sql语句
            String sql = "update dept set dname = '销售部',loc = '天津' where deptno = 20";
            int i = statement.executeUpdate(sql);
            System.out.println(i == 1 ? "执行成功" : "执行失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            if (conn != null) {
                try {
                    conn.close();
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
        }
    }
}
