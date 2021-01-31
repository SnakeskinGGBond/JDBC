package com.jbp.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 实现功能：
 * 解决SQL注入问题
 */
public class JDBCTest07 {
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

        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        Connection conn = null;
        //预编译的数据库操作对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            //此处的SQL语句，是一个框架，每个?是1个占位符，将来接受"值"
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            ps = conn.prepareStatement(sql);
            //通过ps.setString/Int...方法来为占位符?赋值,下标从1开始
            //第一个?
            ps.setString(1,loginName);
            //第二个?
            ps.setString(2,loginPwd);
            //执行SQL
            rs = ps.executeQuery();
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
