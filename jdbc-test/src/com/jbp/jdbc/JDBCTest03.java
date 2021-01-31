package com.jbp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 注册驱动的另一种方式（常用）
 */
public class JDBCTest03 {
    public static void main(String[] args) {
        try {
            //Class.forName()方法执行的时候会加载静态代码块中的代码
            //"com.mysql.cj.jdbc.Driver"类的静态代码块中有：DriverManager.registerDriver(new Driver());
            //以下方法不接收返回值,只用类加载这个动作
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jbp?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true","root","jxp602190516");
            System.out.println(conn);
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

        }
    }
}
