package com.example.diary_0200.DAO;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

public class mypageDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3306/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "root";
    static final String MARIADB_PW = "@seongjun12";

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

    public ResultSet getHighestTime(String date, int seq) {
        String SQL = "SELECT * FROM (SELECT DATE_FORMAT(date, '%Y-%m-%d') AS date, tag, time, seq FROM goal_t UNION SELECT DATE_FORMAT(date, '%Y-%m-%d') AS date, tag, time,seq FROM goal_sw)a WHERE date=? AND a.seq=? ORDER BY time DESC LIMIT 1;";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, date);
            ps.setInt(2, seq);
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
            rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String loadMyPageName(int seq) {
        String SQL = "SELECT name FROM user WHERE seq=?";
        String name = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();
            rs.next();
            name = rs.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void newbe(int seq){
        String SQL = "INSERT INTO mypage VALUES(?,null,null)";
        try{
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

