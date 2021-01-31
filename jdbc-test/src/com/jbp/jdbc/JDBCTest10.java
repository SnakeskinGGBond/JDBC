package com.jbp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * JDBC事务机制
 * 1、JDBC中的事务是自动提交的
 */
public class JDBCTest10 {
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
            //此处可以将自动提交机制改为手动提交
            conn.setAutoCommit(false);//开启事务
            String sql = "update dept set dname = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"x部门");
            ps.setInt(2,30);
            //执行SQL语句
            int i = ps.executeUpdate();
            System.out.println(i);
            //此处加断点，当程序执行到此处时，30部门的名称已改为x部门，i = 1
            String a = null;
            //a.toString();

            //重新给占位符赋值
            ps = conn.prepareStatement(sql);
            ps.setString(1,"y部门");
            ps.setInt(2,20);
            //执行SQL语句
            i += ps.executeUpdate();
            System.out.println(i == 2 ? "修改成功！":"修改失败！");

            //程序走到这，说明以上程序没有问题，事务结束，手动提交
            conn.commit();

        } catch (Exception e) {
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
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
