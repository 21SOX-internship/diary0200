package com.example.diary_0200.DAO;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

public class mypageDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3307/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;

    public mypageDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet loadMyPageInfo(){
        int seq = 0;
        try {
            rs = loadName();
            seq = rs.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQL = "SELECT * FROM mypage where seq=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet loadName() {
        String SQL = "SELECT seq, name FROM user WHERE id='abcd'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

