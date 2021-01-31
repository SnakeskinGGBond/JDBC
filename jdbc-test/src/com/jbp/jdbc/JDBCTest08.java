package com.jbp.jdbc;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 必须使用SQL注入的情况，使用Statement
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        //用户在控制台输入desc就是降序，输入asc就是升序
        String keyWords = initUI();

        arrange(keyWords);
    }

    private static void arrange(String keyWords) {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        //执行SQL
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            //SQL注入时，注意空格
            String sql = "select ename from emp order by ename " + keyWords;
            rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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

    private static String initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入：desc / asc");
        String keyWords = s.next();
        return keyWords;
    }
}
