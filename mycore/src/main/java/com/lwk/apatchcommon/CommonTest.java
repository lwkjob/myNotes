package com.lwk.apatchcommon;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.*;
import java.util.Properties;

/**
 * Created by liwenke on 16/3/29.
 */
public class CommonTest {


    public static void main(String[] args) throws Exception {
        new CommonTest().test1();
    }


    public void test1() throws Exception {
        Properties properties = new Properties();
        properties.load(CommonTest.class.getClassLoader().getResourceAsStream("dpcp.properties"));
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(properties));

        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
//        dataSource.setUrl("jdbc:mysql://10.18.19.43:3307/wfcrm?useUnicode=true&characterEncoding=utf8");
//        dataSource.setUsername("wfcrm");
//        dataSource.setPassword("wfcrm_dev");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        DataSourceConnectionFactory connectionFactory = new DataSourceConnectionFactory(dataSource);

        Connection connection = connectionFactory.createConnection();
        Statement preparedStatement = connection.createStatement();
        ResultSet resultSet = preparedStatement.executeQuery("SELECT * from actor limit 1,3");
        int count=resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i=1;i<count;i++){

                System.out.print("\t"+ metaData.getColumnName(i) +"="+ resultSet.getString(i));
            }
            System.out.println("===");
        }


    }

}
