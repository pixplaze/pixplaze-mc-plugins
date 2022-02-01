package com.pixplaze.simpleeventtracker.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class SqlDatabase {

    private String user;
    private String password;
    private String host;
    private int port;
    private String dbname;

    private boolean useMysql = true;

    public SqlDatabase (String host, int port, String dbname, String user, String password) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

//    public Connection getConnection(String dbUrl) {
//        if (useMysql) {
//            return DriverManager.getConnection()
//        }
//    }
//
//    private String getMysqlUrl() {
//        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useSSL=false";
//        return url;
//    }
//
//    private String getSqliteUrl() {
//        String url = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "database.db";
//    }

}
