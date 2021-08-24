package com.example.diary_0200.DAO;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

public class mypageDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3306/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
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

    public void saveMessage(int seq, String message) {
        String SQL = "UPDATE mypage SET message=? WHERE seq=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, message);
            ps.setInt(2, seq);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getDate(int seq) {
        String SQL = "SELECT DISTINCT date FROM (SELECT seq, DATE_FORMAT(date, '%Y-%m-%d')AS date FROM goal_t UNION SELECT seq, DATE_FORMAT(date, '%Y-%m-%d')AS date FROM goal_sw)a where a.seq=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getHighestTime(String date) {
        String SQL = "SELECT * FROM \n" +
                "(SELECT DATE_FORMAT(date, '%Y-%m-%d') AS date, tag, time, seq FROM goal_t UNION\n" +
                "SELECT DATE_FORMAT(date, '%Y-%m-%d') AS date, tag, time,seq FROM goal_sw)a WHERE date=? AND a.seq=1 ORDER BY time DESC LIMIT 1;";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, date);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet loadMyPageInfo(int seq) {
        String SQL = "select * FROM mypage WHERE seq=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}

