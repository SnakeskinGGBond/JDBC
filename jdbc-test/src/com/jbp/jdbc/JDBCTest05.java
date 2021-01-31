package com.jbp.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest05 {
    public static void main(String[] args) {

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            //获取数据库操作对象
            statement = conn.createStatement();
            //执行sql
            String sql = "select empno,ename,sal as gongzi from emp";
            rs = statement.executeQuery(sql);
            //处理查询结果集
            while (rs.next()) {
                //光标行有数据
                //取数据
                //getString()方法的特点是：不管数据库中的数据类型是什么，都已String的形式取出
                /*String empno = rs.getString(1);//列数，JDBC中所有下标从1开始，不是从0开始
                String ename = rs.getString(2);
                String sal = rs.getString(3);*/

                /*//可以跟查询结果列名，建议跟查询结果列名
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");
                String sal = rs.getString("gongzi");*/

                //除了以String类型取出外，还可以以特定的类型取出,并且可以直接参与数学运算
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                double sal = rs.getDouble("gongzi");
                System.out.println(empno + "," + ename + "," + (sal + 100));

                //System.out.println(empno+","+ename+","+sal);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            if (rs != null) {
                try {
                    rs.close();
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
