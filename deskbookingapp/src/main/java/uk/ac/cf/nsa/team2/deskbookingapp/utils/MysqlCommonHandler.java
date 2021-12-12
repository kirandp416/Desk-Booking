package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlCommonHandler {

    public static Connection getConn(String url,String username,String password,String Driver) throws Exception {
        Class.forName(Driver);
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }
}
