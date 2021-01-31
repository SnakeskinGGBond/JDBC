package com.jbp.jdbc;

import java.sql.*;

public class JDBCTest01 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            //1、注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();//多态，父类型引用指向子类型对象，这个Driver是mysql。jdbcjar包里 ，对java.sql.Driver的实现
            DriverManager.registerDriver(driver);
            //2、获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/jbp?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
            String user = "root";
            String Password = "jxp602190516";
            conn = DriverManager.getConnection(url,user, Password);
            //3、获取数据库操作对象 Statement对象
            statement = conn.createStatement();
            //4、执行sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','北京')";
            //专门执行DML的(insert，delete，update)
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功" : "保存失败");
            //5、处理查询结果集

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6、释放资源
            //为了保证资源一定释放，在finally语句块中释放资源
            //要遵循从小到大依次关闭
            //分别try  catch
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }



    }
}
