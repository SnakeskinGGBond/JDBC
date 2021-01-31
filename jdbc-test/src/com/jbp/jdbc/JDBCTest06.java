package com.jbp.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 实现功能：
 * 1、模拟用户登录功能的实现
 * 2、业务描述：
 * 提供输入的入口，可以输入用户名密码
 * 用户名密码输入后，提交信息，java程序收集到用户信息
 * java程序连接数据库验证用户名密码是否合法
 * 合法：显示登陆成功
 * 不合法：显示登陆失败
 * 3、数据的准备
 * 在实际开发中表的设计会使用专业的建模工具，PowerDesigner
 * 使用PD工具来进行数据库表的设计
 * 4、当前程序存在的问题
 * 用户名：fdsa
 * 密码：fdsa' or '1=1
 * 登录成功
 * 这种现象被称为SQL注入
 */
public class JDBCTest06 {
    public static void main(String[] args) {
        //初始化界面
        Map<String, String> userLoginInfo = initUI();
        //验证用户名密码
        boolean loginSuccess = login(userLoginInfo);
        //输出结果
        System.out.println(loginSuccess ? "登录成功！" : "登录失败");
    }

    /**
     * 用户登录
     *
     * @param userLoginInfo 用户登录信息
     * @return true表示登录成功，false表示登录失败
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        boolean loginSuccess = false;
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "select * from t_user where loginName = '"+userLoginInfo.get("loginName")+"' and loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
            rs = statement.executeQuery(sql);
            //处理结果集
            if (rs.next()){
                loginSuccess = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null){
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     *
     * @return 用户输入的用户名和密码等登陆信息
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = s.next();
        System.out.println("密码：");
        String loginPwd = s.next();

        Map<String, String> userLoginInfo = new HashMap<String, String>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;
    }


}
