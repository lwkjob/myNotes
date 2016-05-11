package com.lwk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by liwenke on 16/4/22.
 */
public class SimpleJdbc {


    String url = "jdbc:mysql://localhost:3306/lwktest";
    String driverstr = "com.mysql.jdbc.Driver";
    String username = "root";
    String password = "";

    private Connection getConnection() throws Exception{
        Class.forName(driverstr);
        return DriverManager.getConnection(url, username, password);
    }

    public  void testQuery() throws  Exception{
        Connection connection = getConnection();
        String sql="select * from ttd ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        boolean execute = preparedStatement.execute();
        System.out.println("查询状态=="+execute);
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String versions=resultSet.getString(3);
            System.out.println("id="+id+" name="+name+" versions="+versions);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public  void testUpdate() throws  Exception{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        String sql="update ttd set name='程序提交的19',versions='9'  where id=7 and versions=8";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println("更新状态1="+i);
        TimeUnit.SECONDS.sleep(10);
        connection.commit();
        System.out.println("1提交了");
        preparedStatement.close();
        connection.close();
    }
    public  void testUpdate2() throws  Exception{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        String sql="update ttd set name='程序提交的29',versions='9'  where id=7 and versions=8";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println("更新状态2="+i);
        TimeUnit.SECONDS.sleep(10);
        connection.commit();
        System.out.println("2提交了");
        preparedStatement.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception{
      Thread t2=  new Thread(()->{
            try {
                new SimpleJdbc().testUpdate();

            }catch (Exception e){
                System.out.println("更新1报错了"+e);
            }
        });
        t2.start();

      Thread t1=  new Thread(()->{
            try {
                new SimpleJdbc().testUpdate2();

            }catch (Exception e){
                System.out.println("更新2报错了2"+e);
            }
        });
        t1.start();
        t2.join();
        t1.join();
        System.out.println();
        new SimpleJdbc().testQuery();
    }
}
