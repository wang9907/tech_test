package com.summer.tech.apache.common.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.mysql.jdbc.Statement;

public class DBCPUtils {
    private static DataSource ds;//定义一个连接池对象
    static{
        try {
            Properties pro = new Properties();
            pro.load(DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
            ds = BasicDataSourceFactory.createDataSource(pro);//得到一个连接池对象
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化连接错误，请检查配置文件！");
        }
    }
    //从池中获取一个连接
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();//关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}