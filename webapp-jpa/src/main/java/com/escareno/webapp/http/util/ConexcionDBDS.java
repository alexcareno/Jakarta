package com.escareno.webapp.http.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexcionDBDS {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "root";


    public static Connection getConnection() throws SQLException {
        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/mysqldb");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ds.getConnection();
    }

}
