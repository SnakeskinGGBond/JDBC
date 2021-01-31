package com.jbp.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * PrepareStatement完成增删改insert delete update
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            //获取预编译的数据库操作对象,增
            /*String sql = "insert into dept (deptno,dname,loc) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            //传值
            ps.setInt(1,60);
            ps.setString(2,"销售部");
            ps.setString(3,"伤害");*/

            /*//改
            String sql = "update dept set loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            //传值
            ps.setString(1,"上海");
            ps.setInt(2,60);*/

            //删
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            //传值
            ps.setInt(1, 60);

            //执行SQL语句
            int i = ps.executeUpdate();
            System.out.println(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
